package snabbdom

import scala.scalajs.js

object Snabbdom {
  def init(
      classModule: Boolean = false,
      propsModule: Boolean = false,
      attributesModule: Boolean = false,
      datasetModule: Boolean = false,
      styleModule: Boolean = false,
      eventlistenersModule: Boolean = false
  ): PatchFunction =
    SnabbdomFacade.init(
      js.Array(
          Some(SnabbdomFacade.classModule).filter(_ => classModule),
          Some(SnabbdomFacade.propsModule).filter(_ => propsModule),
          Some(SnabbdomFacade.attributesModule).filter(_ => attributesModule),
          Some(SnabbdomFacade.datasetModule).filter(_ => datasetModule),
          Some(SnabbdomFacade.styleModule).filter(_ => styleModule),
          Some(SnabbdomFacade.eventListenersModule).filter(_ => eventlistenersModule)
        )
        .collect { case Some(module) => module }
    )
}
