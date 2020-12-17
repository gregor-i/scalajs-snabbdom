package snabbdom

import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSImport}
import scala.scalajs.js.{Dictionary, UndefOr, |}

@js.native
@JSImport("snabbdom/h", name = "h")
private object SnabbdomH extends js.Object {
  def apply(sel: String, props: Data, children: js.Array[String | VNode]): VNode = js.native
}

@js.native
@JSImport("snabbdom/init", name = "init")
private object SnabbdomInit extends js.Object {
  def apply(modules: js.Array[_]): PatchFunction = js.native
}

@JSImport("snabbdom/modules/class", name = "classModule")
@js.native
private object ClassModule extends js.Object

@JSImport("snabbdom/modules/attributes", name = "attributesModule")
@js.native
private object AttrsModule extends js.Object

@JSImport("snabbdom/modules/dataset", name = "datasetModule")
@js.native
private object DatasetModule extends js.Object

@JSImport("snabbdom/modules/eventlisteners", name = "eventListenersModule")
@js.native
private object EventListenerModule extends js.Object

@JSImport("snabbdom/modules/props", name = "propsModule")
@js.native
private object PropsModule extends js.Object

@JSImport("snabbdom/modules/style", name = "styleModule")
@js.native
private object StyleModule extends js.Object

@JSGlobal
@js.native
abstract class VNode extends js.Object {
  def sel: String
  def data: Data
  def children: UndefOr[Array[VNode]]
  def text: UndefOr[String]
  def elm: UndefOr[HTMLElement]
  def key: UndefOr[String | Double | Int]
}

private[snabbdom] class Data(
    val key: Key,
    val `class`: Dictionary[Boolean],
    val props: Dictionary[js.Any],
    val attrs: Dictionary[String],
    val dataset: Dictionary[String],
    val style: Dictionary[String],
    val on: Dictionary[Eventlistener],
    val hook: Dictionary[Hook]
) extends js.Object
