package ong

import br.com.caelum.vraptor._

@Resource
class Lancamentos(result : Result) {

  @Get(Array("/"))
  def x() = {
    println("asldkjaslkdjaskldjkj")
    result.include("asd", "valor")
  }
}