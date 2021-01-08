package snabbdom

import org.scalatest.funsuite.AnyFunSuite
import snabbdom.Syntax._

class SyntaxSpec extends AnyFunSuite{
  test("convert strings to selectors"){
    val node: Node = "div"

    assert(node == Node("div"))
  }

  test("allows direct usage of setter methods"){
    assert("div".classes("bold") == Node("div").classes("bold"))
  }

  test("allows direct usage of extended methods"){
    assert("div".modify(_.classes("bold")) == Node("div").classes("bold"))
  }

  test("modify allows to pass a function to set data") {
    assert(Node("div").modify(_.classes("bold")) == Node("div").classes("bold"))
  }

  test("maybeModify allows to pass an Option to set data conditionally") {
    assert(Node("div").maybeModify(Some("bold"))(_.classes(_)) == Node("div").classes("bold"))
    assert(Node("div").maybeModify(None)(_.classes(_)) == Node("div"))
  }

  test("maybeModify allows to pass a condition to set data conditionally") {
    assert(Node("div").maybeModify(true)(_.classes("bold")) == Node("div").classes("bold"))
    assert(Node("div").maybeModify(false)(_.classes("bold")) == Node("div"))
  }
}
