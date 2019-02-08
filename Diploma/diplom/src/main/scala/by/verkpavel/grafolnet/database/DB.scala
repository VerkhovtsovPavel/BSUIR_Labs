package by.verkpavel.grafolnet.database

import java.awt.image.BufferedImage
import java.io.{ByteArrayInputStream, File}
import javax.imageio.ImageIO

import by.verkpavel.grafolnet.database.dao.{SampleDAO, UserDAO}
import by.verkpavel.grafolnet.database.domain.{Sample, SampleQueryParams, User, UserQueryParams}
import com.mongodb.casbah.Imports._
import by.verkpavel.grafolnet.database.dao.Conversions._

/**
 * Created by Pavel_Verkhovtsov on 5/11/17.
 */
object DB {
  def getSampleByID(id: String) = sampleDAO.findOneById(new ObjectId(id)).get

  val usersDAO = new UserDAO()
  val sampleDAO = new SampleDAO()

  def getImageParamsByID(id: String): Map[String, Double] = sampleDAO.findOneById(new ObjectId(id)).get.handwriteFeatures

  def getImageByID(id: String): BufferedImage = ImageIO.read(new ByteArrayInputStream(getSampleByID(id).imageSource))

  def getImages(userID: ObjectId): Seq[Sample] = sampleDAO.find(SampleQueryParams(user_id = Some(userID))).toList

  def addSample(sample: Sample): String = sampleDAO.insert(sample).get.toHexString

  def updateSample(sample: Sample) = sampleDAO.save(sample)

  def addUser(user: User): String = {
    if (usersDAO.find(UserQueryParams(name = Some(user.name))).isEmpty)
      usersDAO.insert(user).get.toHexString
    else
      ""
  }

  def deleteSampleByID(id: String) = { sampleDAO.removeById(new ObjectId(id)); id }

  def isUserExist(userName: String, password: String): Option[User] = usersDAO.findOne(UserQueryParams(name = Some(userName), passwordHash = Some(password)))
}