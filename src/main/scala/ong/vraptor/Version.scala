package ong.vraptor

import br.com.caelum.vraptor.Intercepts
import br.com.caelum.vraptor.interceptor.Interceptor
import br.com.caelum.vraptor.resource.ResourceMethod
import br.com.caelum.vraptor.core.InterceptorStack
import br.com.caelum.vraptor.Result
import java.util.Date

@Intercepts
class Version(result : Result) extends Interceptor {

  def intercept(stack : InterceptorStack, method : ResourceMethod, resourceInstance : Object) {
    result.include("version", Version())
    stack.next(method, resourceInstance)
  }

  def accepts(method : ResourceMethod) = true

}

object Version {
  lazy val number = new Date().getTime()
  def apply() = number
}