package by.verkpavel.iofs.main

import by.verkpavel.iofs.hash.HashTable
import scala.io.Source
import by.verkpavel.iofs.utils.Chronometry.time

object Main extends App{
  bTreeTest("res/test1000 000.txt")
  hashTableTest("res/test1000 000.txt")

def bTreeTest(fileName : String) = {
  println("----------------------")
  println("BTree")
  println("----------------------")
  val tree = new by.verkpavel.iofs.btree.nm.BTree(5)

  println("Add elements")
  time(Source.fromFile(fileName).mkString.split("""\s+""").foreach((x:String)=>tree.add(x)))

  println("Find elements")
  time(tree.search("02iGTy7RZg"))
  time(tree.search("Word"))
  time(tree.search("CK6p08ZaAP"))

  println("Delete element")
  time(tree.remove("CK6p08ZaAP"))
}


  def hashTableTest(fileName : String ) = {
    println("----------------------")
    println("HashTable")
    println("----------------------")
    val hashTable = new HashTable[String](1000)

    println("Add elements")
    time(Source.fromFile(fileName).mkString.split("""\s+""").foreach(hashTable + _))

    println("Find elements")
    time(hashTable ? "02iGTy7RZg")
    time(hashTable ? "Word")
    time(hashTable ? "CK6p08ZaAP")

    println("Delete element")
    time(hashTable - "CK6p08ZaAP")
  }
}




