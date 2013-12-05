package ong

import java.util.{ List => JList }
import scala.collection.JavaConverters._
import java.sql.Date
import java.text.SimpleDateFormat
import java.text.DecimalFormat

case class Lancamento(id : Long, formaPagamento : FormaPagamento, data : Date, items : Seq[Item]) {
  private val format = new SimpleDateFormat("HH:mm:ss")
  def getItems = items.asJava
  def getFormaPagamento = formaPagamento
  def getHora = format.format(data)
}

case class Item(id : Long, lancamentoId : Long, produto : String, valor : BigDecimal) {
  private val format = new DecimalFormat("'R$'0.00")
  def getProduto = produto
  def getValor = format.format(valor.bigDecimal)
}
