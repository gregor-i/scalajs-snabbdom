package toasts

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

object Toasts {
  def successToast(text: String): Int = ToastsApp.addSyncToast(text, ToastType.Success)
  def dangerToast(text: String): Int  = ToastsApp.addSyncToast(text, ToastType.Danger)
  def warningToast(text: String): Int = ToastsApp.addSyncToast(text, ToastType.Warning)

  def asyncToast[A](
      progressText: String,
      process: Future[A]
  )(onComplete: Try[A] => (ToastType, String))(implicit ex: ExecutionContext): Int =
    ToastsApp.addAsyncToast(progressText, process, onComplete, ex)

  def removeToast(id: Int): Unit = ToastsApp.removeToast(id)
}
