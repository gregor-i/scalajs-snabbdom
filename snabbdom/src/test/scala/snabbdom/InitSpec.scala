package snabbdom

import org.scalatest.funsuite.AnyFunSuite

import scala.scalajs.js.{UndefOr, typeOf}

class InitSpec extends AnyFunSuite {
  test("initialize with no modules") {
    val patch = Snabbdom.init()
    assert(typeOf(patch) == "function")
  }

  test("initialize with all build-in modules") {
    val patch = Snabbdom.init(
      classModule = true,
        propsModule= true,
        attributesModule= true,
        datasetModule= true,
        styleModule= true,
        eventlistenersModule= true
    )
    assert(typeOf(patch) == "function")
  }

  test("all build-in modules are defined"){
    def moduleIsDefined(module: Module): Boolean =
      module.asInstanceOf[UndefOr[_]].isDefined

    assert(moduleIsDefined(SnabbdomFacade.classModule))
    assert(moduleIsDefined(SnabbdomFacade.attributesModule))
    assert(moduleIsDefined(SnabbdomFacade.datasetModule))
    assert(moduleIsDefined(SnabbdomFacade.eventListenersModule))
    assert(moduleIsDefined(SnabbdomFacade.propsModule))
    assert(moduleIsDefined(SnabbdomFacade.styleModule))
  }
}
