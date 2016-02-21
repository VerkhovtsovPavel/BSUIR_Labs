package by.verkpavel.iofs.main

import by.verkpavel.iofs.btree.{BTreeAlg, BTree}
import by.verkpavel.iofs.hash.HashTable
import scala.io.Source
import by.verkpavel.iofs.utils.Chronometry.time

import scala.util.Random

object Main extends App{

  bTreeTest()
  hashTableTest()


def bTreeTest() = {
  println("----------------------")
  println("BTreeAlg")
  println("----------------------")
  val tree = new BTreeAlg[String,String]()

  println("Add elements")
  time(Source.fromFile("res/test1000 000.txt").mkString.split("""\s+""").foreach((x:String)=>tree.put(x,x)))

  println("Find elements")
  time(tree.get("02iGTy7RZg"))
  time(tree.get("dwsdsdsf"))
  time(tree.get("CK6p08ZaAP"))

  println("Delete element")
  //time(tree.delete("CK6p08ZaAP"))
}


  def hashTableTest() = {
    println("----------------------")
    println("HashTable")
    println("----------------------")
    val hashTable = new HashTable[String](1000)

    println("Add elements")
    time(Source.fromFile("res/test1000 000.txt").mkString.split("""\s+""").foreach(hashTable + _))

    println("Find elements")
    time(hashTable ? "02iGTy7RZg")
    time(hashTable ? "dwsdsdsf")
    time(hashTable ? "CK6p08ZaAP")

    println("Delete element")
    time(hashTable - "CK6p08ZaAP")
  }
}




