package ong

import java.util.GregorianCalendar
import br.com.caelum.stella.boleto.{ Boleto => StellaBoleto, Datas, Emissor, Sacado }
import br.com.caelum.stella.boleto.bancos.Bradesco
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto
import br.com.caelum.vraptor.{ Get, Resource, Result }
import br.com.caelum.vraptor.ioc.{ Component, RequestScoped }

@Resource
@RequestScoped
class Boleto(result : Result) {

  @Get(Array("/boleto"))
  def boleto() = {
  }

  @Get(Array("/boleto2"))
  def generate(vencimento : Vencimento) = {
    val datas = Datas.novasDatas()
      .comDocumento(new GregorianCalendar())
      .comProcessamento(new GregorianCalendar())
      .comVencimento(vencimento.dia, vencimento.mes, vencimento.ano);

    val emissor = Emissor.novoEmissor()
      .comCedente("Associação Natureza em Forma")
      .comAgencia("3349").comDigitoAgencia("9")
      .comContaCorrente("5800").comDigitoContaCorrente("9")
      .comEndereco("Pagável em qualquer agência bancária até o vencimento")
      .comNossoNumero("123123"); // eu que gero

    val sacado = Sacado.novoSacado()
      .comNome("Fulano da Silva")

    val banco = new Bradesco();

    val boleto = StellaBoleto.novoBoleto()
      .comBanco(banco)
      .comDatas(datas)
      .comEmissor(emissor)
      .comSacado(sacado)
      .comValorBoleto("5.00")

    val pdf = new GeradorDeBoleto(boleto).geraPDF

  }
}

case class Vencimento(dia : Int, mes : Int, ano : Int)