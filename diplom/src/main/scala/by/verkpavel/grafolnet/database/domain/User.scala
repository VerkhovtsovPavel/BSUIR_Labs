package by.verkpavel.grafolnet.database.domain

import com.mongodb.casbah.Imports.ObjectId

case class User(
  _id: ObjectId = new ObjectId,
  name: String,
  passwordHash: String,
  isAdmin: Boolean
)

case class UserQueryParams(
  _id: Option[ObjectId] = None,
  name: Option[String] = None,
  passwordHash: Option[String] = None,
  isAdmin: Option[Boolean] = None
)