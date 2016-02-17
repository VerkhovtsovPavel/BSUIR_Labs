package by.verkpavel.iofs.main

import by.verkpavel.iofs.btree.BTree
import by.verkpavel.iofs.hash.HashTable
import scala.io.Source
import by.verkpavel.iofs.utils.Chronometry.time

object Main extends App{

  bTreeTest()
  hashTableTest()

def bTreeTest() = {
  println("BTree")
  val tree = new BTree[String]()

  println("Add elements")
  time(Source.fromFile("res/test1000 000.txt").mkString.split("""\s+""").foreach(tree.insert))

  println("Find elements")
  println("Try found 02iGTy7RZg "+time(tree.isMember("02iGTy7RZg")))
  println("Try found dwsdsdsf "+time(tree.isMember("dwsdsdsf")))
  println("Try found CK6p08ZaAP "+time(tree.isMember("CK6p08ZaAP")))

  println("Delete element")
  time(tree.delete("CK6p08ZaAP"))

  println("Find elements")
  println("Try found CK6p08ZaAP "+time(tree.isMember("CK6p08ZaAP")))
}


  def hashTableTest() = {
    println("HashTable")
    val hashTable = new HashTable[String](1000)

    println("Add elements")
    time(Source.fromFile("res/test1000 000.txt").mkString.split("""\s+""").foreach(hashTable + _))

    println("Find elements")
    println("Try found 02iGTy7RZg "+time(hashTable ? "02iGTy7RZg"))
    println("Try found dwsdsdsf "+time(hashTable ? "dwsdsdsf"))
    println("Try found CK6p08ZaAP "+time(hashTable ? "CK6p08ZaAP"))

    println("Delete element")
    time(hashTable - "CK6p08ZaAP")

    println("Find elements")
    println("Try found CK6p08ZaAP "+time(hashTable ? "CK6p08ZaAP"))
  }
}




