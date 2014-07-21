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
import jaview.CachedJaview
import javax.servlet.http.HttpServletResponse
import jaview.CachedJaview

@Resource
class Caixa(result : Result, lancamentos : Lancamentos) {

  @Get(Array("/"))
  def novo() = {
    val l = lancamentos.hoje

    result.use(classOf[JaviewView])(_("/caixa/novo")
      (new Totais(l), l, 0 until 15, lancamentos.dias, lancamentos.meses))
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
    val l = lancamentos.doDia(dia)
    result.use(classOf[JaviewView])(_("/caixa/antigo")
      (new Totais(l), l, lancamentos.dias, lancamentos.meses))
  }

  @Get(Array("/fechamento/{mes}"))
  def fechamento(mes : String) = {
    result.use(classOf[Csv]).render(mes, lancamentos.doMes(mes))
  }
}

case class PartialItem(produto : String, valor : BigDecimal, quantidade : Long) {
  def empty = produto == "" || valor == null
}

class Totais(lancamentos : Seq[Lancamento]) {

  import ong.{ FormaPagamento => FP }

  val format = new DecimalFormat("'R$ '0.00")

  def dinheiro = sumOf(FP.dinheiro, lancamentos)
  def credito = sumOf(FP.credito, lancamentos)
  def debito = sumOf(FP.debito, lancamentos)
  def transferencia = sumOf(FP.transferencia, lancamentos)
  def deposito = sumOf(FP.deposito, lancamentos)
  def cheque = sumOf(FP.cheque, lancamentos)
  def pagamentoOnline = sumOf(FP.pagamentoOnline, lancamentos)

  private def sumOf(forma : FormaPagamento, lancamentos : Seq[Lancamento]) =
    format.format(lancamentos.filter(_.formaPagamento == forma).
      map(_.items.map(i => i.valor * i.quantidade).sum).sum)
}

object Views {
  val cache = new CachedJaview
}

@RequestScoped
@Component
class JaviewView(res : HttpServletResponse) extends View {

  def apply(f : CachedJaview => String) = {
    val html = f(Views.cache)
    res.setContentType("text/html")
    res.getOutputStream().print(html)
  }
}
