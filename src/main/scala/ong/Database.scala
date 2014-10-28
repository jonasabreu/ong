package ong

import java.util.{ List => JList }
import scala.collection.JavaConverters._
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.RequestScoped
import scala.collection.mutable.ListBuffer
import scala.slick.jdbc.JdbcBackend.{ Database => SQDB }
import scala.slick.driver.SQLiteDriver.simple._
import scala.slick.jdbc.JdbcBackend.Database.dynamicSession
import java.sql.Date
import java.sql.Timestamp
import scala.slick.jdbc.{ GetResult, StaticQuery => Q }
import java.text.SimpleDateFormat
import scala.io.Source
import java.text.DecimalFormat

@Component
@RequestScoped
class Lancamentos {

  import Database._

  def add(formaPagamento: FormaPagamento, atendente: String, items: Seq[PartialItem]) = onDatabase {
    val lancamentos = TableQuery[LancamentosTable].map { l => (l.formaPagamento, l.atendente) }
    val id = (lancamentos returning TableQuery[LancamentosTable].map(_.id)) += ((formaPagamento.toString, atendente))

    TableQuery[ItemsTable].map { i => (i.lancamentoId, i.produto, i.valor, i.quantidade) }.insertAll(
      items.map {
        case PartialItem(produto, valor, quantidade) =>
          (id, produto, BigDecimal.javaBigDecimal2bigDecimal(valor), quantidade)
      }: _*)
  }

  def doMes(mes: String) = onDatabase {
    val q = Q[String, (String, String, String, String, String, String)] + queryFor("fechamento")

    q(mes).list.
      map {
        case (a, b, c, d, e, f) => a :: b :: c :: d :: e :: f :: Nil
      }
  }

  private def queryFor(id: String): String =
    Source.fromInputStream(classOf[Lancamentos].
      getResourceAsStream(s"/query/${id}.sql")).
      getLines.
      mkString(" ")

  def remove(id: Long) = onDatabase {
    TableQuery[LancamentosTable].filter(_.id === id).delete
  }

  def doId(id: Long): Lancamento = onDatabase {
    val q = for { l <- TableQuery[LancamentosTable] if l.id === id } yield l
    q.list.map(tupleToLancamento).apply(0)
  }

  def muda(id: Long, formaPagamento: FormaPagamento) = onDatabase {
    val q = for { l <- TableQuery[LancamentosTable] if l.id === id } yield l.formaPagamento
    q.update(formaPagamento.toString)
  }

  def dias = datasNoFormato("%Y-%m-%d")

  def meses = datasNoFormato("%Y-%m")

  private def datasNoFormato(formato: String) = onDatabase {
    Q.queryNA[(String)](s"select distinct strftime('${formato}', datetime(createdAt, 'localtime')) as data from lancamentos order by data desc").list
  }

  def hoje = doDia(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()))

  def doDia(date: String): Seq[Lancamento] = onDatabase {
    val query =
      Q[String, (Long, String, Date, String, Boolean)] + s"select id, formaPagamento, strftime('%Y-%m-%d %H:%M:%f', createdAt, 'localtime'), atendente, notaEmitida from lancamentos where date(createdAt, 'localtime') == ?"

    query(date).list.reverse.map(tupleToLancamento)
  }

  def tupleToLancamento(t: (Long, String, Date, String, Boolean)) = t match {
    case (id, formaPagamento, date, atendente, notaEmitida) =>
      val query = for {
        item <- TableQuery[ItemsTable] if item.lancamentoId === id
      } yield item.*

      val items = query.list.map { t =>
        Item(t._1, t._2, t._3, t._4, t._5)
      }
      Lancamento(id, FormaPagamento.valueOf(formaPagamento), date, atendente, notaEmitida, items)
  }
}

@RequestScoped
@Component
class Items {
  import Database._

  def distinct = onDatabase {
    val format = new DecimalFormat("0.00")
    Q.queryNA[(String, BigDecimal)](s"""select distinct lower(produto), valor from items group by produto having count(produto) > 1 order by produto asc;""").
      list.map {
        case (produto, valor) => JsonItem(produto, format.format(valor))
      }
  }

}

case class JsonItem(produto: String, valor: String)

class LancamentosTable(tag: Tag) extends Table[(Long, String, Date, String, Boolean)](tag, "lancamentos") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def formaPagamento = column[String]("formaPagamento")
  def createdAt = column[Date]("createdAt")
  def atendente = column[String]("atendente")
  def notaEmitida = column[Boolean]("notaEmitida")

  def * = (id, formaPagamento, createdAt, atendente, notaEmitida)
}

class ItemsTable(tag: Tag) extends Table[(Long, Long, String, BigDecimal, Long)](tag, "items") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def lancamentoId = column[Long]("lancamento_id")
  def produto = column[String]("produto")
  def valor = column[BigDecimal]("valor")
  def quantidade = column[Long]("quantidade")

  def * = (id, lancamentoId, produto, valor, quantidade)
  def insertProj = (id.?, lancamentoId, produto, valor, quantidade)
}

object Database {
  def onDatabase[T](f: => T) = SQDB.forURL("jdbc:sqlite:ong.db", driver = "org.sqlite.JDBC").withDynSession(f)
}
