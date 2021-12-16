package snabbdom

import org.scalajs.dom.PointerEvent
import org.scalatest.funsuite.AnyFunSuite

import scala.scalajs.js

class NodeSpec extends AnyFunSuite {
  def dynamic(any: js.Any): js.Dynamic = any.asInstanceOf[js.Dynamic]

  val parent = Node("div")

  test("text") {
    val vnode = parent.text("text").toVNode

    assert(dynamic(vnode).children.length == dynamic(1))
    assert(dynamic(vnode).children.selectDynamic("0").text == dynamic("text"))
  }

  test("child") {
    val vnode = parent.child(Node("img")).toVNode

    assert(dynamic(vnode).children.length == dynamic(1))
    assert(dynamic(vnode).children.selectDynamic("0").sel == dynamic("img"))
  }

  test("child with iterable") {
    val vnode = parent.child(Seq(Node("img"), Node("span"))).toVNode

    assert(dynamic(vnode).children.length == dynamic(2))
    assert(dynamic(vnode).children.selectDynamic("0").sel == dynamic("img"))
    assert(dynamic(vnode).children.selectDynamic("1").sel == dynamic("span"))
  }

  test("childOptional with None") {
    val vnode = parent.childOptional(None).toVNode
    assert(dynamic(vnode).children.length == dynamic(0))
  }

  test("childOptional with Some") {
    val vnode = parent.childOptional(Some(Node("img"))).toVNode
    assert(dynamic(vnode).children.length == dynamic(1))
    assert(dynamic(vnode).children.selectDynamic("0").sel == dynamic("img"))
  }

  test("children") {
    val vnode = parent
      .children(
        Seq(Node("img")),
        Node("span")
      )
      .toVNode

    assert(dynamic(vnode).children.length == dynamic(2))
    assert(dynamic(vnode).children.selectDynamic("0").sel == dynamic("img"))
    assert(dynamic(vnode).children.selectDynamic("1").sel == dynamic("span"))
  }

  test("hookInit") {
    val vnode = parent.hookInit(_ => ()).toVNode
    assert(dynamic(vnode).data.hook.init !== js.undefined)
  }

  test("hookCreate") {
    val vnode = parent.hookCreate((_, _) => ()).toVNode
    assert(dynamic(vnode).data.hook.create !== js.undefined)
  }

  test("hookDestroy") {
    val vnode = parent.hookDestroy(_ => ()).toVNode
    assert(dynamic(vnode).data.hook.destroy !== js.undefined)
  }

  test("hookInsert") {
    val vnode = parent.hookInsert(_ => ()).toVNode
    assert(dynamic(vnode).data.hook.insert !== js.undefined)
  }

  test("hookPrepatch") {
    val vnode = parent.hookPrepatch((_, _) => ()).toVNode
    assert(dynamic(vnode).data.hook.prepatch !== js.undefined)
  }

  test("hookUpdate") {
    val vnode = parent.hookUpdate((_, _) => ()).toVNode
    assert(dynamic(vnode).data.hook.update !== js.undefined)
  }

  test("hookPostpatch") {
    val vnode = parent.hookPostpatch((_, _) => ()).toVNode
    assert(dynamic(vnode).data.hook.postpatch !== js.undefined)
  }

  test("hookRemove") {
    val vnode = parent.hookRemove((_, _) => ()).toVNode
    assert(dynamic(vnode).data.hook.remove !== js.undefined)
  }

  test("event with abstract Event") {
    val vnode = parent.event[Event]("click", _ => ()).toVNode
    assert(dynamic(vnode).data.on.click !== js.undefined)
  }

  test("event with specific Event") {
    val vnode = parent.event[PointerEvent]("click", _ => ()).toVNode
    assert(dynamic(vnode).data.on.click !== js.undefined)
  }
}
