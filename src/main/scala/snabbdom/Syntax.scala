package snabbdom

trait Syntax {
  implicit def stringAsSelector(sel: String): Node = Node(sel)
}

object Syntax extends Syntax
