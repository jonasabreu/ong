package ong

import java.util.{ List => JList }
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.RequestScoped
import scala.collection.mutable.ListBuffer
import scala.slick.session.{ Database => SQDB }
import scala.slick.driver.SQLiteDriver.simple._
import scala.slick.session.Database.threadLocalSession
import math.BigDecimal._

@Component
@RequestScoped
class Lancamentos {

  import Database._

  def add(lancamento : Lancamento) = onDatabase {
    val id = Lancamentos.autoInc.insert(None, lancamento.formaPagamento.toString())
    Items.autoInc.insertAll(
      lancamento.items.asScala.
        map(_.copy(lancamentoId = id)).
        map(Item.unapply(_).get).
        map {
          case (a, b, c, d) => (None, b, c, javaBigDecimal2bigDecimal(d))
        } : _*)
  }

  def todos : Seq[Lancamento] = onDatabase {
    val query = for {
      lancamento <- Lancamentos
    } yield (lancamento.id, lancamento.formaPagamento)

    query.list.map {
      case (id, formaPagamento) =>
        val query = for {
          item <- Items if item.lancamentoId === id
        } yield item.*

        val items = query.list.map { t =>
          Item(t._1, t._2, t._3, t._4.bigDecimal)
        }
        Lancamento(id, FormaPagamento.valueOf(formaPagamento), items.toList.asJava)
    }
  }
}

object Lancamentos extends Table[(Long, String)]("lancamentos") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def formaPagamento = column[String]("formaPagamento")
  def * = id ~ formaPagamento
  def items = Items.where(_.lancamentoId === id)
  def autoInc = id.? ~ formaPagamento returning id
}

object Items extends Table[(Long, Long, String, BigDecimal)]("items") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def lancamentoId = column[Long]("lancamento_id")
  def produto = column[String]("produto")
  def valor = column[BigDecimal]("valor")
  def * = id ~ lancamentoId ~ produto ~ valor
  def autoInc = id.? ~ lancamentoId ~ produto ~ valor
}

object Database {
  def onDatabase[T](f : => T) = SQDB.forURL("jdbc:sqlite:ong.db", driver = "org.sqlite.JDBC").withSession(f)
}