package snabbdom

import scala.scalajs.js
import scala.scalajs.js.{Dictionary, |}

case class Node(
    private val sel: String,
    private val key: Key = (),
    private val classes: Seq[(String, Boolean)] = Seq.empty,
    private val props: Seq[(String, js.Any)] = Seq.empty,
    private val attrs: Seq[(String, String)] = Seq.empty,
    private val dataset: Seq[(String, String)] = Seq.empty,
    private val styles: Seq[(String, String)] = Seq.empty,
    private val events: Seq[(String, Eventlistener)] = Seq.empty,
    private val hooks: Seq[(String, Hook)] = Seq.empty,
    private val children: Seq[String | Node] = Seq.empty
) {
  def key(key: Key): Node =
    copy(key = key)

  def `class`(className: String, active: Boolean): Node =
    copy(classes = classes :+ (className -> active))

  def `class`(className: String): Node =
    `class`(className, active = true)

  def classes(classes: String*): Node =
    copy(classes = this.classes ++ classes.map(_ -> true))

  def prop(propName: String, value: js.Any): Node =
    copy(props = props :+ (propName -> value))

  def props(props: Seq[(String, js.Any)]): Node =
    copy(props = this.props ++ props)

  def attr(attrName: String, value: String): Node =
    copy(attrs = attrs :+ (attrName -> value))

  def boolAttr(attrName: String, value: Boolean): Node =
    if (value)
      copy(attrs = attrs :+ (attrName -> ""))
    else
      this

  def attrs(attrs: Seq[(String, String)]): Node =
    copy(attrs = this.attrs ++ attrs)

  def data(datasetName: String, value: String): Node =
    copy(dataset = dataset :+ (datasetName -> value))

  def dataset(dataset: Seq[(String, String)]): Node =
    copy(dataset = this.dataset ++ dataset)

  def style(styleName: String, value: String): Node =
    copy(styles = styles :+ (styleName -> value))

  def styles(styles: Seq[(String, String)]): Node =
    copy(styles = this.styles ++ styles)

  def event[E <: Event](on: String, listener: js.Function1[E, Unit]): Node =
    copy(events = events :+ (on -> listener))

  def events(events: Seq[(String, Eventlistener)]): Node =
    copy(events = this.events ++ events)

  def hookInit(hook: js.Function1[VNode, Unit]): Node =
    copy(hooks = hooks :+ ("init" -> hook))

  def hookCreate(hook: js.Function2[VNode, VNode, Unit]): Node =
    copy(hooks = hooks :+ ("create" -> hook))

  def hookInsert(hook: js.Function1[VNode, Unit]): Node =
    copy(hooks = hooks :+ ("insert" -> hook))

  def hookPrepatch(hook: js.Function2[VNode, VNode, Unit]): Node =
    copy(hooks = hooks :+ ("prepatch" -> hook))

  def hookUpdate(hook: js.Function2[VNode, VNode, Unit]): Node =
    copy(hooks = hooks :+ ("update" -> hook))

  def hookPostpatch(hook: js.Function2[VNode, VNode, Unit]): Node =
    copy(hooks = hooks :+ ("postpatch" -> hook))

  def hookDestroy(hook: js.Function1[VNode, Unit]): Node =
    copy(hooks = hooks :+ ("destroy" -> hook))

  def hookRemove(hook: js.Function2[VNode, js.Function0[Unit], Unit]): Node =
    copy(hooks = hooks :+ ("remove" -> hook))

  def hooks(hooks: Seq[(String, Hook)]): Node =
    copy(hooks = this.hooks ++ hooks)

  def text(child: String): Node =
    copy(children = children :+ child)

  def child(node: Node): Node =
    copy(children = children :+ node)

  def child(nodes: Iterable[Node]): Node =
    copy(children = children ++ nodes.asInstanceOf[Iterable[String | Node]])

  def childOptional(nodes: Option[Node]): Node =
    copy(children = children ++ nodes.asInstanceOf[Option[String | Node]])

  def children(nodes: (Node | Iterable[Node])*): Node =
    child(nodes.flatMap[Node] {
      case seq: Iterable[_] => seq.asInstanceOf[Iterable[Node]]
      case elem             => Seq(elem).asInstanceOf[Iterable[Node]]
    }: Iterable[Node])

  def toVNode: VNode = SnabbdomFacade.h(
    sel = sel,
    props = new Data(
      key = key,
      `class` = Dictionary(classes: _*),
      props = Dictionary(props: _*),
      attrs = Dictionary(attrs: _*),
      dataset = Dictionary(dataset: _*),
      style = Dictionary(styles: _*),
      on = Dictionary(events: _*),
      hook = Dictionary(hooks: _*)
    ),
    children = js.Array(children: _*).map { x =>
      if (x.isInstanceOf[String]) x.asInstanceOf[String]
      else x.asInstanceOf[Node].toVNode
    }
  )
}
