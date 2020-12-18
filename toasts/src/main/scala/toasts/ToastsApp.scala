package toasts

import org.scalajs.dom
import snabbdom.Syntax._
import snabbdom.{Event, Node, PatchFunction, Snabbdom, VNode}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

private sealed trait Toast {
  def id: Int
}
private case class SyncToast(id: Int, toastType: ToastType, text: String) extends Toast
private case class AsyncToast[A](
    id: Int,
    progressText: String,
    process: Future[A],
    onComplete: Try[A] => (ToastType, String),
    ex: ExecutionContext
) extends Toast

private[toasts] object ToastsApp {
  private var counter: Int        = 0
  private var toasts: List[Toast] = List.empty

  private var node: Option[VNode] = None

  private val patch: PatchFunction = Snabbdom.init(
    classModule = true,
    styleModule = true,
    eventlistenersModule = true
  )

  private def render(): Unit = {
    val ui = "toast-bar"
      .child(toasts.map {
        case toast: SyncToast     => renderSyncToast(toast)
        case toast: AsyncToast[_] => renderFutureToast(toast)
      })
      .toVNode

    node match {
      case None =>
        val container = dom.document.createElement("toast-bar")
        dom.document.body.appendChild(container)
        node = Some(patch(container, ui))
      case Some(vnode) =>
        node = Some(patch(vnode, ui))
    }
  }

  private def renderFutureToast[A](toast: AsyncToast[A]): Node =
    toast.process.value match {
      case None =>
        notification(ToastType.InProcess, toast.progressText)
          .key(toast.id)
          .hookInsert { _ =>
            toast.process.onComplete(_ => render())(toast.ex)
          }

      case Some(value) =>
        val (toastType, text) =
          toast.onComplete(value)

        notification(toastType, text)
          .key(toast.id)
          .child("button.delete".event[Event]("click", _ => removeToast(toast.id)))
          .style("transition", "0.5s")
          .hookPostpatch { (vnode, _) =>
            dom.window.setTimeout(() => vnode.elm.get.style.opacity = "0", 2000)
            dom.window.setTimeout(() => removeToast(toast.id), 2500)
          }
    }

  private def renderSyncToast(toast: SyncToast): Node =
    notification(toast.toastType, toast.text)
      .key(toast.id)
      .child("button.delete".event[Event]("click", _ => removeToast(toast.id)))
      .style("transition", "0.5s")
      .hookInsert { vnode =>
        dom.window.setTimeout(() => vnode.elm.get.style.opacity = "0", 2000)
        dom.window.setTimeout(() => removeToast(toast.id), 2500)
        ()
      }

  private def notification(toastType: ToastType, text: String): Node =
    "div.notification"
      .classes(toastType.`class`)
      .child(
        "div.media"
          .child(
            "figure.media-left"
              .child("span.icon".child("i.fas.fa-lg".classes(toastType.iconClasses: _*)))
          )
          .child("div.media-content".text(text))
      )

  def addSyncToast(text: String, toastType: ToastType): Int = {
    val id    = { counter += 1; counter }
    val toast = SyncToast(id, toastType, text)
    toasts = toast :: toasts
    render()
    id
  }

  def addAsyncToast[A](
      progressText: String,
      process: Future[A],
      onComplete: Try[A] => (ToastType, String),
      ex: ExecutionContext
  ): Int = {
    val id    = { counter += 1; counter }
    val toast = AsyncToast(id, progressText, process, onComplete, ex)
    toasts = toast :: toasts
    render()
    id
  }

  def removeToast(id: Int): Unit = {
    toasts = toasts.filter(_.id != id)
    render()
  }
}
