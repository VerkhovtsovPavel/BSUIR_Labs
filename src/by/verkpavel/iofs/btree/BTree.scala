package by.verkpavel.iofs.btree


class BTree[T] {


  sealed class BTreeItem
  case class Leaf(val values : List[T]) extends BTreeItem
  case class NodeItem(val child : BTreeItem, val value : T) extends BTreeItem
  case class Node(val items : List[NodeItem]) extends BTreeItem
}


