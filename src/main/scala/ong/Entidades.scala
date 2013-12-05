package ong

import java.util.{ List => JList }
import scala.collection.JavaConverters._

case class Lancamento(id : Long, formaPagamento : FormaPagamento, items : JList[Item]) {
  def getItems = items
  def getFormaPagamento = formaPagamento
  def nonEmptyItems = items.asScala.filterNot(_.empty).asJava
}

case class Item(id : Long, lancamentoId : Long, produto : String, valor : java.math.BigDecimal) {
  def getProduto = produto
  def getValor = valor
  def empty = produto == "" || valor == null
}
