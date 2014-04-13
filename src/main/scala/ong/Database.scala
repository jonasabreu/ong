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

@Component
@RequestScoped
class Lancamentos {

  import Database._

  def add(formaPagamento : FormaPagamento, atendente : String, items : Seq[PartialItem]) = onDatabase {
    val id = Lancamentos.insertProj.insert((None, formaPagamento.toString, atendente))

    Items.insertProj.insertAll(
      items.map {
        case PartialItem(produto, valor, quantidade) => (None, id, produto, BigDecimal.javaBigDecimal2bigDecimal(valor), quantidade)
      } : _*)
  }

  def remove(id : Long) = onDatabase {
    Lancamentos.where(_.id === id).delete
  }

  def dias = onDatabase {
    val query = for {
      lancamento <- Lancamentos
    } yield lancamento.createdAt
    val formatter = new SimpleDateFormat("yyyy-MM-dd")
    query.list.map { date =>
      formatter.format(date)
    }.sortWith(_ > _).distinct
  }

  def hoje = doDia(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()))

  def doDia(date : String) : Seq[Lancamento] = onDatabase {
    val query =
      Q.queryNA[(Long, String, Date, String)](s"select * from lancamentos where date(createdAt) == '${date}'")

    query.list.reverse.map {
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

object Lancamentos extends Table[(Long, String, Date, String)]("lancamentos") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def formaPagamento = column[String]("formaPagamento")
  def createdAt = column[Date]("createdAt")
  def atendente = column[String]("atendente")

  def * = id ~ formaPagamento ~ createdAt ~ atendente
  def items = Items.where(_.lancamentoId === id)
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
