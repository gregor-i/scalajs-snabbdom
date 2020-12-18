import scala.scalajs.js
import scala.scalajs.js.{UndefOr, |}

package object snabbdom {
  type Event   = org.scalajs.dom.Event
  type Element = org.scalajs.dom.Element

  type PatchFunction = js.Function2[VNode | Element, VNode, VNode]

  type Hook = js.Function0[Unit] | js.Function1[VNode, Unit] | js.Function2[VNode, VNode, Unit] | js.Function2[VNode, js.Function0[Unit], Unit]

  type Eventlistener = js.Function1[_ <: Event, Unit]
  type Key           = UndefOr[String | Double | Int]
}
