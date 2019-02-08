package by.verkpavel.grafolnet.database.dao

import by.verkpavel.grafolnet.database.domain.{Sample, SampleQueryParams, User, UserQueryParams}
import com.mongodb.DBObject
import com.novus.salat._

import scala.language.implicitConversions

object Conversions {
  implicit def sampleToDBObject(s: Sample): DBObject =
    grater[Sample].asDBObject(s)

  implicit def sampleParamsToDBObject(s: SampleQueryParams): DBObject =
    grater[SampleQueryParams].asDBObject(s)

  implicit def userToDBObject(c: User): DBObject =
    grater[User].asDBObject(c)

  implicit def userParamsToDBObject(u: UserQueryParams): DBObject =
    grater[UserQueryParams].asDBObject(u)
}
