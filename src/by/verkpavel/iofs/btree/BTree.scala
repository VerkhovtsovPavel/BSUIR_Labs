package by.verkpavel.iofs.btree

class BTree[T <: Ordered[T]](nodeSize : Int, listSize : Int) {

  val root = Node(List[T](), List[Node](), null)

  private def search (item : T, node : BTreeItem = root) : (Boolean, Leaf) = {
    node match {
      case Node(items, links, _) => val link = items.count((i) => i < item);  search(item, links.apply(link))
      case leaf @ Leaf(values, _) => (values.contains(item), leaf)
    }
  }

  def ? (item : T) = search(item)._1

  def + (item : T) = {
    val leaf = search(item)._2

    if(leaf.values.size<listSize)
    {
      leaf.values = leaf.values :+ item
      leaf.values.sortWith(_ < _)
    }
    else if(leaf.parent.items.size < nodeSize){
      val parentNode = leaf.parent
      val newLeaf = Leaf(leaf.values.slice(listSize/2, listSize), leaf.parent)
      leaf.values = leaf.values.slice(0, listSize/2)
      parentNode.items = parentNode.items :+ newLeaf.values.head
      parentNode.links =  parentNode.links :+ newLeaf
    }else{}

  }

  sealed class BTreeItem
  case class Leaf(var values : List[T], parent: Node) extends BTreeItem
  case class Node(var items : List[T],  var links : List[BTreeItem], parent : Node) extends BTreeItem
}


