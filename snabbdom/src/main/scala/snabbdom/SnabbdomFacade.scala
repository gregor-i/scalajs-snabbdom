package snabbdom

import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSImport}
import scala.scalajs.js.{Dictionary, UndefOr, |}

@js.native
@JSImport("snabbdom", name = JSImport.Namespace)
private object SnabbdomFacade extends js.Object {
  def h(sel: String, props: Data, children: js.Array[String | VNode]): VNode = js.native

  def init(modules: js.Array[Module]): PatchFunction = js.native

  val classModule: Module          = js.native
  val attributesModule: Module     = js.native
  val datasetModule: Module        = js.native
  val eventListenersModule: Module = js.native
  val propsModule: Module          = js.native
  val styleModule: Module          = js.native
}

trait Module extends js.Object

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
