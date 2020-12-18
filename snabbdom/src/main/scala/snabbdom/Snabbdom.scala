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
  ) =
    SnabbdomInit(
      js.Array(
          Some(ClassModule).filter(_ => classModule),
          Some(PropsModule).filter(_ => propsModule),
          Some(AttrsModule).filter(_ => attributesModule),
          Some(DatasetModule).filter(_ => datasetModule),
          Some(StyleModule).filter(_ => styleModule),
          Some(EventListenerModule).filter(_ => eventlistenersModule)
        )
        .collect { case Some(module) => module }
    )
}
