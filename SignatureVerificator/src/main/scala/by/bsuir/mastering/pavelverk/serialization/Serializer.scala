package by.bsuir.mastering.pavelverk.serialization

import java.io.{FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

object Serializer {

  val objectStorage = "storage/"

  def serialize(obj : Any with Serializable) : String = {
    val fileName = objectStorage + StringUtil.generateRandomString(7, ALPHANUM)
    serialize(obj, fileName)
  }

  def serialize(obj : Any with Serializable, filePath : String) : String = {
    val oos = new ObjectOutputStream(new FileOutputStream(filePath))
    oos.writeObject(obj)
    oos.close()
    filePath
  }

  def deserialize[T](fileName : String) : T = {
    val ois = new ObjectInputStream(new FileInputStream(fileName))
    val instance = ois.readObject.asInstanceOf[T]
    ois.close()
    instance
  }

}
