package testUtil

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("jsdom", name = "JSDOM")
class JsDom(fixture: String) extends js.Object

object JsDom{
  def setupSimulatedDom(): Unit = {
    val simulatedContext = new JsDom("<!DOCTYPE html><p>Hello world</p>").asInstanceOf[js.Dynamic]

    js.Dynamic.global.global.window = simulatedContext.window
    js.Dynamic.global.global.document = simulatedContext.window.document
  }
}
