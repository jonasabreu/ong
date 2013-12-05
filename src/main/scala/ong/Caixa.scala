package ong

import br.com.caelum.vraptor._
import scala.collection.JavaConverters._
import java.math.BigDecimal
import scala.collection.mutable.ListBuffer
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.RequestScoped

@Resource
class Caixa(result : Result, lancamentos : Lancamentos) {

  import Caixa._

  @Get(Array("/"))
  def novo() = {
    val todosLancamentos = lancamentos.todos

    result.include("lancamentos", todosLancamentos.asJava)
    result.include("totalDebito", sumOf(FormaPagamento.debito, todosLancamentos))
    result.include("totalDinheiro", sumOf(FormaPagamento.dinheiro, todosLancamentos))
    result.include("totalCredito", sumOf(FormaPagamento.credito, todosLancamentos))
  }

  @Post(Array("/novo"))
  def postaNovo(lancamento : Lancamento) = {
    lancamentos.add(lancamento.copy(items = lancamento.nonEmptyItems))
    result.redirectTo(classOf[Caixa]).novo
  }
}

object Caixa {
  def sumOf(forma : FormaPagamento, lancamentos : Seq[Lancamento]) =
    lancamentos.filter(_.formaPagamento == forma).
      map(_.getItems.asScala.map(_.valor).foldLeft(new BigDecimal("0"))(_.add(_))).
      foldLeft(new BigDecimal("0"))(_.add(_))

}