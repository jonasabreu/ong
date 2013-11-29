package ong

import br.com.caelum.vraptor._
import scala.collection.JavaConverters._
import java.math.BigDecimal
import scala.collection.mutable.ListBuffer
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.RequestScoped

@Resource
class Caixa(result : Result, lancamentos : Lancamentos) {

  @Get(Array("/"))
  def novo() = {
    result.include("lancamentos", lancamentos.todos.asJava)
  }

  @Post(Array("/novo"))
  def postaNovo(lancamento : Lancamento) = {
    lancamentos.add(lancamento.copy(items = lancamento.nonEmptyItems))
    result.redirectTo(classOf[Caixa]).novo
  }
}

