package ong

import java.util.{ List => JList }
import scala.collection.JavaConverters._
import java.sql.Date
import java.text.SimpleDateFormat
import java.text.DecimalFormat

case class Lancamento(id : Long, formaPagamento : FormaPagamento, data : Date, atendente : String, items : Seq[Item]) {
  private val format = new SimpleDateFormat("HH:mm:ss")
  private val moneyFormat = new DecimalFormat("'R$ '0.00")
  def getItems = items.asJava
  def getFormaPagamento = formaPagamento
  def getHora = format.format(data)
  def getId = id
  def getTotal = moneyFormat.format(items.map(i => i.valor * i.quantidade).sum)
  def getAtendente = if ("" == atendente) "N&atilde;o Identificado" else atendente
}

case class Item(id : Long, lancamentoId : Long, produto : String, valor : BigDecimal, quantidade : Long) {
  private val format = new DecimalFormat("'R$ '0.00")
  def getProduto = produto
  def getValor = format.format((valor * quantidade).bigDecimal)
  def getQuantidade = quantidade
}
