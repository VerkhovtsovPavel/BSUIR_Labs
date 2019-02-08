package by.verkpavel.grafolnet.database.domain

import com.mongodb.casbah.Imports.ObjectId

case class Sample(
  _id: ObjectId = new ObjectId,
  user_id: ObjectId,
  imageFormat: String,
  imageSource: Array[Byte],
  var handwriteFeatures: Map[String, Double] = Map[String, Double](),
  var natureDescription: String = ""
)

case class SampleQueryParams(
  _id: Option[ObjectId] = None,
  user_id: Option[ObjectId] = None,
  imageFormat: Option[String] = None,
  imagePath: Option[String] = None,
  var handwriteFeatures: Option[Map[String, Double]] = None,
  var natureDescription: Option[String] = None
)
