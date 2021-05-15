package testUtil

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("jsdom", name = JSImport.Default)
object JsDomFacade extends js.Object {
  @js.native
  class JSDOM(fixture: String) extends js.Object {
    def window: dom.Window = js.native
  }
}

object JsDom{
  def setupSimulatedDom(): Unit = {
    val simulatedContext = new JsDomFacade.JSDOM("<!DOCTYPE html><p>Hello world</p>")

    js.Dynamic.global.global.window = simulatedContext.window
    js.Dynamic.global.global.document = simulatedContext.window.document
  }
}
