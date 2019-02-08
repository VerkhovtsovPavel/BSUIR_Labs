package by.bsuir.mastering.pavelverk.serialization

import java.io._

object Serializer {

  val objectStorage = "storage/"
  new File(objectStorage).createNewFile()

  def serialize(obj : Any with Serializable) : String = {
    val newID = StringUtil.generateRandomString(7, ALPHANUM)
    serialize(obj, newID)
  }

  def serialize(obj : Any with Serializable, id : String) : String = {
    val filePath = objectStorage + id
    val oos = new ObjectOutputStream(new FileOutputStream(filePath))
    oos.writeObject(obj)
    oos.close()
    id
  }

  def deserialize[T](id : String) : T = {
    val filePath = objectStorage + id
    val ois = new ObjectInputStream(new FileInputStream(filePath))
    val instance = ois.readObject.asInstanceOf[T]
    ois.close()
    instance
  }

}
