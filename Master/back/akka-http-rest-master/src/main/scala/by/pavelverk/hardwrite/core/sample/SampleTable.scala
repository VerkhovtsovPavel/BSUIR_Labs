package by.pavelverk.hardwrite.core.sample

import by.pavelverk.hardwrite.core.{Sample, SampleId, UserId}
import by.pavelverk.hardwrite.utils.db.DatabaseConnector
import slick.ast.BaseTypedType
import slick.jdbc.JdbcType

private[sample] trait SampleTable {

  protected val databaseConnector: DatabaseConnector
  import databaseConnector.profile.api._

  implicit def listDouble2String: JdbcType[List[Double]] with BaseTypedType[List[Double]] = MappedColumnType.base[List[Double], String] (
    list => list.mkString(";"),
    string => string.split(";").map(_.toDouble).toList
  )

  class Samples(tag: Tag) extends Table[Sample](tag, "sample") {

    def id = column[SampleId]("id", O.PrimaryKey)
    def user_id = column[UserId]("user_id")
    def times = column[List[Double]]("times")
    def x = column[List[Double]]("x")
    def y = column[List[Double]]("y")
    def e = column[List[Double]]("e")

    override def * = (id, user_id, times, x, y, e) <> ((Sample.apply _).tupled, Sample.unapply)
  }

  protected val samples = TableQuery[Samples]
}
