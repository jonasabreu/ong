package ong

import br.com.caelum.vraptor._
import scala.collection.JavaConverters._
import java.math.BigDecimal
import scala.collection.mutable.ListBuffer
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.RequestScoped
import java.util.{ List => JList }
import ong.FormaPagamento._
import java.text.DecimalFormat

@Resource
class Caixa(result : Result, lancamentos : Lancamentos) {

  import Caixa._

  @Get(Array("/"))
  def novo() = {
    val lancamentos = this.lancamentos.hoje
    result.include("campos", (0 until 15).asJava)

    adiciona(lancamentos)
  }

  private def adiciona(lancamentos : Seq[Lancamento]) = {
    val format = new DecimalFormat("'R$ '0.00")

    result.include("lancamentos", lancamentos.asJava)
    FormaPagamento.values.foreach { forma =>
      result.include(s"total${forma.toString.capitalize}", format.format(sumOf(forma, lancamentos)))
    }
  }

  @Post(Array("/novo"))
  def postaNovo(formaPagamento : FormaPagamento, items : JList[PartialItem]) = {
    lancamentos.add(formaPagamento, items.asScala.filterNot(_.empty))
    result.redirectTo(classOf[Caixa]).novo
  }

  @Get(Array("/remove/{id}"))
  def removePagamento(id : Long) = {
    lancamentos.remove(id)
    result.redirectTo(classOf[Caixa]).novo
  }

  @Get(Array("/antigos"))
  def lista = {
    result.include("dias", lancamentos.dias.asJava)
  }

  @Get(Array("/antigo/{dia}"))
  def antigo(dia : String) = {
    adiciona(lancamentos.doDia(dia))
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