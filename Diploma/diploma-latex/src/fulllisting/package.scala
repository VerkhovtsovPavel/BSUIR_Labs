package by.verkpavel.grafolnet.model

case class ImageRequest(id: Int, image: String, params: Array[String])
case class ImageResponse(id: Int, params: Map[String, Any])
