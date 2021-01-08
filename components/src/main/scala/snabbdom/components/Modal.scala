package snabbdom.components

import snabbdom.{Event, Node}
import snabbdom.Syntax._

object Modal {
  def apply(closeAction: Option[Event => Unit] = None, background: Option[Node] = None)(content: Node*): Node =
    "div.modal.is-active"
      .child(
        "div.modal-background"
          .maybeModify(closeAction)(_.event("click", _))
          .style("display", "flex")
          .childOptional(background)
      )
      .child("div.modal-content".child("div.box".child(content)))
}
