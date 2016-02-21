package by.verkpavel.iofs.btree


class BTree[T <: Ordered[T]](nodeSize : Int, listSize : Int) {

  val root = Node(List[NodeItem](), List[Node]())

  def ? (item : T, node : BTreeItem = root) : Boolean = {
    node match {
      case Node(items, links) => val link = items.count((i) => i.value < item);  ?(item, links.apply(link))
      case Leaf(values) => values.contains(item)
    }
  }

  sealed class BTreeItem
  case class Leaf(val values : List[T]) extends BTreeItem
  case class NodeItem(val value : T) extends BTreeItem
  case class Node(val items : List[NodeItem], val links : List[BTreeItem]) extends BTreeItem
}


