package snabbdom.components

import snabbdom.Node

object Icon {
  def apply(icon: String): Node =
    Node("span.icon")
      .child(
        Node("i.fas").`class`(icon)
      )
}
