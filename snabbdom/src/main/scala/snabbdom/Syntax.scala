package snabbdom

trait Syntax {
  given stringAsSelector: Conversion[String, Node] = s => Node.apply(sel = s)

  extension (node: Node) {
    def modify(op: Node => Node): Node = op(node)

    def maybeModify[A](option: Option[A])(setter: (Node, A) => Node): Node =
      option.fold(node)(setter(node, _))

    def maybeModify(condition: Boolean)(set: Node => Node): Node =
      if (condition) set(node) else node
  }
}

object Syntax extends Syntax
