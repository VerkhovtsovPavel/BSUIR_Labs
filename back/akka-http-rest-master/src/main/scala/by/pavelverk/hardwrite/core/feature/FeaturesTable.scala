package by.pavelverk.hardwrite.core.feature

import by.pavelverk.hardwrite.core.{Features, FeaturesId, SampleId}
import by.pavelverk.hardwrite.utils.db.DatabaseConnector
import slick.ast.BaseTypedType
import slick.jdbc.JdbcType

private[feature] trait FeaturesTable {

  protected val databaseConnector: DatabaseConnector
  import databaseConnector.profile.api._

  implicit def listDouble2String: JdbcType[List[Double]] with BaseTypedType[List[Double]] = MappedColumnType.base[List[Double], String] (
    list => list.mkString(";"),
    string => string.split(";").map(_.toDouble).toList
  )

  class Feature(tag: Tag) extends Table[Features](tag, "features") {

    def id = column[String]("id", O.PrimaryKey)
    def sample_id= column[SampleId]("sample_id")
    def timer = column[Double]("timer")
    def lines = column[Int]("lines")
    def square = column[Double]("square")
    def horizontalLength= column[Int]("horizontalLength")
    def verticalLength= column[Int]("verticalLength")
    def totalLength = column[Int]("totalLength")
    def maxVelocity = column[Double]("maxVelocity")
    def minVelocity = column[Double]("minVelocity")
    def durationX = column[Double]("durationX")
    def durationY = column[Double]("durationY")
    def characterTilt= column[Int]("characterTilt")
    def lineTilt = column[Int]("lineTilt")
    def characterSpacing = column[Double]("characterSpacing")
    def wordSpacing = column[Double]("wordSpacing")
    def lineSpacing = column[Double]("lineSpacing")
    def frequencyOfText = column[Double]("frequencyOfText")


      override def * = (id, sample_id, timer, lines, square, horizontalLength, verticalLength, totalLength, maxVelocity, minVelocity, durationX, durationY, characterTilt, lineTilt, characterSpacing, wordSpacing, lineSpacing, frequencyOfText) <> ((Features.apply _).tupled, Features.unapply)
  }

  protected val features = TableQuery[Feature]
}
