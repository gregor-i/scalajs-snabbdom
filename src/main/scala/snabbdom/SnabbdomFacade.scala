package snabbdom

import org.scalajs.dom
import org.scalajs.dom.Event
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSImport}
import scala.scalajs.js.{Dictionary, UndefOr, |}

object SnabbdomFacade extends js.Object {
  type PatchFunction = js.Function2[VNode | dom.Element, VNode, VNode]

  // note: remove is not considered
  type Hook          = js.Function0[Unit] | js.Function1[VNode, Unit] | js.Function2[VNode, VNode, Unit]
  type Eventlistener = js.Function1[_ <: Event, Unit]
  type Child         = String | VNode
  type Key           = UndefOr[String | Double | Int]
}

@js.native
@JSImport("snabbdom/h", name = "h")
private object SnabbdomH extends js.Object {
  def apply(sel: String, props: Data, children: js.Array[SnabbdomFacade.Child]): VNode = js.native
}

@js.native
@JSImport("snabbdom/init", name = "init")
private object SnabbdomInit extends js.Object {
  def apply(modules: js.Array[_]): SnabbdomFacade.PatchFunction = js.native
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
  def children: UndefOr[Seq[VNode]]
  def text: UndefOr[String]
  def elm: UndefOr[HTMLElement]
  def key: UndefOr[String | Double | Int]
}

private[snabbdom] class Data(
    val key: SnabbdomFacade.Key,
    val `class`: Dictionary[Boolean],
    val props: Dictionary[js.Any],
    val attrs: Dictionary[String],
    val dataset: Dictionary[String],
    val style: Dictionary[String],
    val on: Dictionary[SnabbdomFacade.Eventlistener],
    val hook: Dictionary[SnabbdomFacade.Hook]
) extends js.Object
