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
import ong.vraptor.Csv

@Resource
class Caixa(result : Result, lancamentos : Lancamentos) {

  import Caixa._

  @Get(Array("/"))
  def novo() = {
    val lancamentos = this.lancamentos.hoje
    result.include("campos", (0 until 15).asJava)
    result.include("dias", this.lancamentos.dias.asJava)
    result.include("meses", this.lancamentos.meses.asJava)

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
  def postaNovo(formaPagamento : FormaPagamento, atendente : String, items : JList[PartialItem]) = {
    lancamentos.add(formaPagamento, atendente, items.asScala.filterNot(_.empty))
    result.redirectTo(classOf[Caixa]).novo
  }

  @Get(Array("/remove/{id}"))
  def removePagamento(id : Long) = {
    lancamentos.remove(id)
    result.redirectTo(classOf[Caixa]).novo
  }

  @Post(Array("/mudaFormaPagamento"))
  def mudaFormaPagamento(id : Long, formaPagamento : FormaPagamento) = {
    lancamentos.muda(id, formaPagamento)
    result.redirectTo("/")
  }

  @Get(Array("/antigo/{dia}"))
  def antigo(dia : String) = {
    adiciona(lancamentos.doDia(dia))
  }

  @Get(Array("/fechamento/{mes}"))
  def fechamento(mes : String) = {
    result.use(classOf[Csv]).render(mes, lancamentos.doMes(mes))
  }
}

case class PartialItem(produto : String, valor : BigDecimal, quantidade : Long) {
  def empty = produto == "" || valor == null
}

object Caixa {
  def sumOf(forma : FormaPagamento, lancamentos : Seq[Lancamento]) =
    lancamentos.filter(_.formaPagamento == forma).
      map(_.items.map(i => i.valor * i.quantidade).sum).sum
}