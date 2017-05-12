package by.verkpavel.grafolnet.database

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import by.verkpavel.grafolnet.database.dao.{SampleDAO, UserDAO}
import by.verkpavel.grafolnet.database.domain.{Sample, SampleQueryParams, User, UserQueryParams}
import com.mongodb.casbah.Imports._
import by.verkpavel.grafolnet.database.dao.Conversions._

/**
 * Created by Pavel_Verkhovtsov on 5/11/17.
 */
object DB {

  val usersDAO = new UserDAO()
  val sampleDAO = new SampleDAO()

  def getImageParamsByID(id: String): Map[String, Double] = sampleDAO.findOneById(new ObjectId(id)).get.handwriteFeatures

  def getImageByID(id: Int): BufferedImage = ImageIO.read(new File("imagePath")) //TODO Uncorrect

  def getImages(userID: String): Seq[Sample] = sampleDAO.find(SampleQueryParams(user_id = Some(new ObjectId(userID)))).toList

  def addSample(sample: Sample): String = sampleDAO.insert(sample).get.toHexString

  def updateSample(sample: Sample) = sampleDAO.save(sample)

  def addUser(user: User): String = usersDAO.insert(user).get.toHexString

  def isUserExist(userName: String, password: String): Boolean = usersDAO.findOne(UserQueryParams(name = Some(userName), passwordHash = Some(password))).isDefined

}