package snabbdom.components

import snabbdom.Node

object ButtonList {
  def right(buttons: Node*): Node    = Node("div.buttons.is-right").child(buttons)
  def left(buttons: Node*): Node    = Node("div.buttons.is-left").child(buttons)
  def centered(buttons: Node*): Node = Node("div.buttons.is-centered").child(buttons)
}
