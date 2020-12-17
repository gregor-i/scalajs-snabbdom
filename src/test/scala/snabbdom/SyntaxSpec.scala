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
}
