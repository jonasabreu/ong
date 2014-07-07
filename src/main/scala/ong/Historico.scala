package ong

import br.com.caelum.vraptor.{ Get, Resource, Result }
import br.com.caelum.vraptor.ioc.{ Component, RequestScoped }
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.RequestScoped
import br.com.caelum.vraptor.View
import java.util.zip.GZIPOutputStream
import javax.servlet.http.HttpServletResponse
import org.json4s._
import org.json4s.native.Serialization

@Resource
@RequestScoped
class Historico(items : Items, result : Result) {

  @Get(Array("/historico/produtos"))
  def produtos = {
    implicit val formats = Serialization.formats(NoTypeHints)
    result.use(classOf[Asset]).json(s"var autocompleteData = ${Serialization.write(items.distinct)} ;")

  }
}

@Component
@RequestScoped
class Asset(response : HttpServletResponse) extends View {
  def json(json : String) {
    response.setContentType("application/json")
    response.setHeader("Content-Encoding", "gzip")

    val stream = new GZIPOutputStream(response.getOutputStream)
    stream.write(json.getBytes())
    stream.close
  }
}