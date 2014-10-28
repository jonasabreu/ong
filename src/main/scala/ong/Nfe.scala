package ong

import br.com.caelum.vraptor.Resource
import br.com.caelum.vraptor.ioc.RequestScoped
import br.com.caelum.vraptor.Get
import br.com.caelum.vraptor.Result

@Resource
class Nfe(result: Result, lancamentos: Lancamentos) {

  @Get(Array("/emite/{lancamentoId}"))
  def emite(lancamentoId: Long) = {

    val lancamento = lancamentos.doId(lancamentoId)

    result.use(classOf[JaviewView])(_("/nfe/emite")(lancamento, lancamentos.dias, lancamentos.meses))

  }

}