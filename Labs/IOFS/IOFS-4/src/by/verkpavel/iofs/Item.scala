package by.verkpavel.iofs

sealed abstract class Item{def weight : Double}

case class Leaf(char : Char, weight : Double) extends Item

case class Node(left : Item, right : Item) extends Item {
  override def weight = left.weight + right.weight
}