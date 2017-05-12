package by.verkpavel.grafolnet.database.domain

import com.mongodb.casbah.Imports.ObjectId

case class Sample(
  _id: ObjectId = new ObjectId,
  user_id: ObjectId,
  imageFormat: String,
  imagePath: String,
  handwriteFeatures: Map[String, Double],
  natureDescription: String
)

case class SampleQueryParams(
  _id: Option[ObjectId] = None,
  user_id: Option[ObjectId] = None,
  imageFormat: Option[String] = None,
  imagePath: Option[String] = None,
  handwriteFeatures: Option[Map[String, Double]] = None,
  natureDescription: Option[String] = None
)
