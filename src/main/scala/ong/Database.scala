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
    val id = Lancamentos.autoInc.insert(None)
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
    } yield lancamento.id

    query.list.map { id =>
      val query = for {
        item <- Items if item.lancamentoId === id
      } yield item.*

      val items = query.list.map { t =>
        Item(t._1, t._2, t._3, t._4.bigDecimal)
      }
      Lancamento(id, items.toList.asJava)
    }

  }

}

case class Lancamento(id : Long, items : JList[Item]) {
  def getItems = items
  def nonEmptyItems = items.asScala.filterNot(_.empty).asJava
}

case class Item(id : Long, lancamentoId : Long, produto : String, valor : java.math.BigDecimal) {
  def getProduto = produto
  def getValor = valor
  def empty = produto == "" || valor == null
}

object Lancamentos extends Table[(Long)]("lancamentos") {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
  def * = id
  def items = Items.where(_.lancamentoId === id)
  def autoInc = id.? returning id
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