package by.verkpavel.grafolnet.database

import java.awt.image.BufferedImage

/**
  * Created by Pavel_Verkhovtsov on 5/11/17.
  */
object DB {
  def getImageParamsByID(id: Int) : Map[String,Double] = ???

  def getImageByID(id: Int) : BufferedImage = ???

  def getImages(userID : Int) : List[Any] = ???

  def addSample(sample : Sample) : Int = ???

  def isUserExist(userName : String, password : String) : Boolean = ???

}

case class Sample