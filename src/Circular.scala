import scala.collection.mutable

class Circular[A](list: Seq[A]) extends Iterator[A]{

  val elements = new mutable.Queue[A] ++= list
  var pos = 0

  def next = {
    if (pos == elements.length)
      pos = 0
    val value = elements(pos)
    pos = pos + 1
    value
  }

  def hasNext = elements.nonEmpty
  def add(a: A): Unit = { elements += a }
  override def toString = elements.toString

}
