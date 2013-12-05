package ong

import br.com.caelum.vraptor._
import scala.collection.JavaConverters._
import java.math.BigDecimal
import scala.collection.mutable.ListBuffer
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.RequestScoped
import java.util.{ List => JList }
import ong.FormaPagamento._

@Resource
class Caixa(result : Result, lancamentos : Lancamentos) {

  import Caixa._

  @Get(Array("/"))
  def novo() = {
    val lancamentos = this.lancamentos.hoje

    result.include("lancamentos", lancamentos.asJava)
    result.include("totalDebito", sumOf(debito, lancamentos))
    result.include("totalDinheiro", sumOf(dinheiro, lancamentos))
    result.include("totalCredito", sumOf(credito, lancamentos))
  }

  @Post(Array("/novo"))
  def postaNovo(formaPagamento : FormaPagamento, items : JList[PartialItem]) = {
    lancamentos.add(formaPagamento, items.asScala.filterNot(_.empty))
    result.redirectTo(classOf[Caixa]).novo
  }
}

case class PartialItem(produto : String, valor : BigDecimal) {
  def empty = produto == "" || valor == null
}

object Caixa {
  def sumOf(forma : FormaPagamento, lancamentos : Seq[Lancamento]) =
    lancamentos.filter(_.formaPagamento == forma).
      map(_.items.map(_.valor).sum).sum
}