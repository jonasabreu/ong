package ong

import br.com.caelum.vraptor.{ Get, Resource, Result }
import br.com.caelum.vraptor.ioc.{ Component, RequestScoped }
import br.com.caelum.vraptor.ioc.Component
import br.com.caelum.vraptor.ioc.RequestScoped
import br.com.caelum.vraptor.View
import scala.pickling._
import json._
import java.util.zip.GZIPOutputStream
import javax.servlet.http.HttpServletResponse

@Resource
@RequestScoped
class Historico(items : Items, result : Result) {

  @Get(Array("/historico/produtos"))
  def produtos = {
    result.use(classOf[Asset]).json(items.distinct.pickle.toString)
  }
}

@Component
@RequestScoped
class Asset(response : HttpServletResponse) extends View {
  def json(json : String) {
    val stream = new GZIPOutputStream(response.getOutputStream)

  }
}