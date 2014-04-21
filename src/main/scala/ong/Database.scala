package ong

import java.util.{ List => JList }
import scala.collection.JavaConverters._
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.RequestScoped
import scala.collection.mutable.ListBuffer
import scala.slick.session.{ Database => SQDB }
import scala.slick.driver.SQLiteDriver.simple._
import scala.slick.session.Database.threadLocalSession
import java.sql.Date
import java.sql.Timestamp
import scala.slick.jdbc.{ GetResult, StaticQuery => Q }
import java.text.SimpleDateFormat
import scala.io.Source

@Component
@RequestScoped
class Lancamentos {

  import Database._

  def add(formaPagamento : FormaPagamento, atendente : String, items : Seq[PartialItem]) = onDatabase {
    val id = Lancamentos.insertProj.insert((None, formaPagamento.toString, atendente))

    Items.insertProj.insertAll(
      items.map {
        case PartialItem(produto, valor, quantidade) =>
          (None, id, produto, BigDecimal.javaBigDecimal2bigDecimal(valor), quantidade)
      } : _*)
  }

  def doMes(mes : String) = onDatabase {
    Q.query[String, (String, String, String, String, String, String)](queryFor("fechamento")).
      list(mes).
      map {
        case (a, b, c, d, e, f) => a :: b :: c :: d :: e :: f :: Nil
      }
  }

  private def queryFor(id : String) : String =
    Source.fromInputStream(classOf[Lancamentos].
      getResourceAsStream(s"/query/${id}.sql")).
      getLines.
      mkString(" ")

  def remove(id : Long) = onDatabase {
    Lancamentos.where(_.id === id).delete
  }

  def dias = datasNoFormato("%Y-%m-%d")

  def meses = datasNoFormato("%Y-%m")

  private def datasNoFormato(formato : String) = onDatabase {
    Q.queryNA[(String)](s"select distinct strftime('${formato}', datetime(createdAt, 'localtime')) as data from lancamentos order by data desc").list
  }

  def hoje = doDia(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()))

  def doDia(date : String) : Seq[Lancamento] = onDatabase {
    val query =
      Q.query[String, (Long, String, Date, String)](s"select id, formaPagamento, strftime('%Y-%m-%d %H:%M:%f', createdAt, 'localtime'), atendente from lancamentos where date(createdAt, 'localtime') == ?")

    query.list(date).reverse.map {
      case (id, formaPagamento, date, atendente) =>
        val query = for {
          item <- Items if item.lancamentoId === id
        } yield item.*

        val items = query.list.map { t =>
          Item(t._1, t._2, t._3, t._4, t._5)
        }
        Lancamento(id, FormaPagamento.valueOf(formaPagamento), date, atendente, items)
    }
  }
}

@RequestScoped
@Component
class Items {
  import Database._

  def distinct = onDatabase {
    Q.queryNA[(String, BigDecimal)](s"select distinct lower(produto), valor from items group by produto having count(produto) > 1 order by produto asc;").
      list
  }

}

object Lancamentos extends Table[(Long, String, Date, String)]("lancamentos") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def formaPagamento = column[String]("formaPagamento")
  def createdAt = column[Date]("createdAt")
  def atendente = column[String]("atendente")

  def * = id ~ formaPagamento ~ createdAt ~ atendente
  def insertProj = id.? ~ formaPagamento ~ atendente returning id
}

object Items extends Table[(Long, Long, String, BigDecimal, Long)]("items") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def lancamentoId = column[Long]("lancamento_id")
  def produto = column[String]("produto")
  def valor = column[BigDecimal]("valor")
  def quantidade = column[Long]("quantidade")

  def * = id ~ lancamentoId ~ produto ~ valor ~ quantidade
  def insertProj = id.? ~ lancamentoId ~ produto ~ valor ~ quantidade
}

object Database {
  def onDatabase[T](f : => T) = SQDB.forURL("jdbc:sqlite:ong.db", driver = "org.sqlite.JDBC").withSession(f)
}
