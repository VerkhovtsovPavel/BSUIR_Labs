package by.pavelverk.hardwrite.core.feature

import by.pavelverk.hardwrite.core.Features
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
    def times = column[List[Double]]("times")
    def x = column[List[Double]]("x")
    def y = column[List[Double]]("y")
    def e = column[List[Double]]("e")

    override def * = (id, times, x, y, e) <> ((Features.apply _).tupled, Features.unapply)
  }

  protected val features = TableQuery[Feature]
}
