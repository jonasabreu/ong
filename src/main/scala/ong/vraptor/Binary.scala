package ong.vraptor

import br.com.caelum.vraptor.View
import javax.servlet.http.HttpServletResponse
import br.com.caelum.vraptor.ioc.RequestScoped
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.Resource

@RequestScoped
@Component
class Binary(response : HttpServletResponse) extends View {

  def pdf(nome : String, pdf : Array[Byte]) {
    response.setContentType("application/pdf")
    response.addHeader("Content-disposition", s"attachment;filename=${nome}.pdf");
    response.getOutputStream().write(pdf)
  }

}