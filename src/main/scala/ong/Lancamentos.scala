package ong

import br.com.caelum.vraptor._
import java.util.List
import scala.collection.JavaConverters._
import java.math.BigDecimal

@Resource
class Lancamentos(result : Result) {

  @Get(Array("/"))
  def novo() = {
  }

  @Post(Array("/novo"))
  def postaNovo(lancamentosList : List[Lancamento]) = {
    lancamentosList.asScala.foreach(println)
  }
}

case class Lancamento(produto : String, valor : BigDecimal)