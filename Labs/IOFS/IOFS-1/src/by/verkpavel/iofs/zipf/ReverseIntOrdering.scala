package by.verkpavel.iofs.zipf

object ReverseIntOrdering extends Ordering[Int] {
  override def compare(x: Int, y: Int): Int = y - x
}