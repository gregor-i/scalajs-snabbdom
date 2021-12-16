package snabbdom

final class EnrichNode(val node: Node) extends AnyVal {
  def modify(op: Node => Node): Node = op(node)

  def maybeModify[A](option: Option[A])(setter: (Node, A) => Node): Node =
    option match {
      case Some(value) => setter(node, value)
      case None        => node
    }

  def maybeModify(condition: Boolean)(set: Node => Node): Node =
    if (condition) set(node) else node
}

trait Syntax {
  implicit def stringAsSelector(sel: String): Node   = Node(sel)
  implicit def enrichNode(node: Node): EnrichNode    = new EnrichNode(node)
  implicit def enrichString(sel: String): EnrichNode = new EnrichNode(Node(sel = sel))
}

object Syntax extends Syntax
