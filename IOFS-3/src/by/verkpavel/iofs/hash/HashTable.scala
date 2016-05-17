package by.verkpavel.iofs.hash

class HashTable[T](val size: Int) {

  val table = Array.fill[List[T]](size)(List())

 def + (item : T): Unit = {
   val hashValue : Int = hashFunction(item)
   if(! ?(item))
    table(hashValue) = table(hashValue) :+ item
  }

  def - (item : T): Unit = {
    val hashValue : Int = hashFunction(item)
    table(hashValue) = table(hashValue).filter(_!=item)
  }

 def ? (item : T) = {
   table(hashFunction(item)).contains(item)
 }

  private def hashFunction(value : T) = {
    math.abs(value.hashCode % size)
  }
}
