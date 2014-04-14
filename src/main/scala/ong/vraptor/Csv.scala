package ong.vraptor

import br.com.caelum.vraptor.View
import javax.servlet.http.HttpServletResponse
import br.com.caelum.vraptor.ioc.RequestScoped
import br.com.caelum.vraptor.ioc.Component

@RequestScoped
@Component
class Csv(response : HttpServletResponse) extends View {

  def render(date : String, cells : List[List[String]]) {
    response.setContentType("text/csv")
    response.addHeader("Content-disposition", s"attachment;filename=fechamento-${date}.csv");
    response.getOutputStream().print(
      cells.
        map(list =>
          list.
            map(e => Option(e).map(_.replaceAll("[;,]", "")).getOrElse("")).
            mkString(";")).
        mkString("\n"))
  }

}