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

@Component
@RequestScoped
class Lancamentos {

  import Database._

  def add(formaPagamento : FormaPagamento, items : Seq[PartialItem]) = onDatabase {
    val id = Lancamentos.insertProj.insert((None, formaPagamento.toString))

    Items.insertProj.insertAll(
      items.map {
        case PartialItem(produto, valor) => (None, id, produto, BigDecimal.javaBigDecimal2bigDecimal(valor))
      } : _*)
  }

  def todos : Seq[Lancamento] = onDatabase {
    val query = for {
      lancamento <- Lancamentos
    } yield lancamento.*

    query.list.reverse.map {
      case (id, formaPagamento, date) =>
        val query = for {
          item <- Items if item.lancamentoId === id
        } yield item.*

        val items = query.list.map { t =>
          Item(t._1, t._2, t._3, t._4)
        }
        Lancamento(id, FormaPagamento.valueOf(formaPagamento), date, items)
    }
  }
}

object Lancamentos extends Table[(Long, String, Date)]("lancamentos") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def formaPagamento = column[String]("formaPagamento")
  def createdAt = column[Date]("createdAt")
  def * = id ~ formaPagamento ~ createdAt
  def items = Items.where(_.lancamentoId === id)
  def insertProj = id.? ~ formaPagamento returning id
}

object Items extends Table[(Long, Long, String, BigDecimal)]("items") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def lancamentoId = column[Long]("lancamento_id")
  def produto = column[String]("produto")
  def valor = column[BigDecimal]("valor")
  def * = id ~ lancamentoId ~ produto ~ valor
  def insertProj = id.? ~ lancamentoId ~ produto ~ valor
}

object Database {
  def onDatabase[T](f : => T) = SQDB.forURL("jdbc:sqlite:ong.db", driver = "org.sqlite.JDBC").withSession(f)
}