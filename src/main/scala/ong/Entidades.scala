package ong

import java.util.{ List => JList }
import scala.collection.JavaConverters._
import java.sql.Date
import java.text.SimpleDateFormat

case class Lancamento(id : Long, formaPagamento : FormaPagamento, data : Date, items : Seq[Item]) {
  private val format = new SimpleDateFormat("HH:mm:ss")
  def getItems = items.asJava
  def getFormaPagamento = formaPagamento
  def getHora = format.format(data)
}

case class Item(id : Long, lancamentoId : Long, produto : String, valor : BigDecimal) {
  def getProduto = produto
  def getValor = valor.bigDecimal
}
