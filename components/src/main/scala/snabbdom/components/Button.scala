package snabbdom.components

import snabbdom.{Event, Node}

object Button {
  def apply(text: String, icon: String, onclick: Event => Unit): Node =
    Node("button.button")
      .event[Event]("click", onclick)
      .child(Icon(icon))
      .child(Node("span").text(text))

  def apply(text: String, onclick: Event => Unit): Node =
    Node("button.button")
      .event[Event]("click", onclick)
      .text(text)

  def icon(icon: String, onclick: Event => Unit, round: Boolean = true): Node =
    Node("button.button")
      .`class`("is-rounded", round)
      .event[Event]("click", onclick)
      .child(Icon(icon))
}
