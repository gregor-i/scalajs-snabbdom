package snabbdom.toasts

import org.scalatest.funsuite.AnyFunSuite
import testUtil.JsDom

import scala.concurrent.Future
import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global

class ToastsSpec extends AnyFunSuite {
  test("sync toasts") {
    JsDom.setupSimulatedDom()
    Toasts.successToast("successToast")
    Toasts.warningToast("warningToast")
    Toasts.dangerToast("dangerToast")
  }

  test("async toasts") {
    JsDom.setupSimulatedDom()
    Toasts.asyncToast(
      "processText",
      Future.successful(15)
    ) {
      case Success(15) => (ToastType.Success, "15 is the result")
      case _           => (ToastType.Danger, "unexpected failure")
    }
  }

}
