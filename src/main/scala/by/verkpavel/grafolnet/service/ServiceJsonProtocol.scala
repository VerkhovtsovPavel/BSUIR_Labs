package by.verkpavel.grafolnet.service

import by.verkpavel.grafolnet.model.{ ImageRequest, ImageResponse }
import spray.json.{ DefaultJsonProtocol, JsFalse, JsNumber, JsString, JsTrue, JsValue, JsonFormat }

trait ServiceJsonProtocol extends DefaultJsonProtocol {

  implicit object AnyJsonFormat extends JsonFormat[Any] {
    def write(x: Any) = x match {
      case n: Int => JsNumber(n)
      case s: String => JsString(s)
      case b: Boolean if b == true => JsTrue
      case b: Boolean if b == false => JsFalse
      case _ => JsString("Parse error")
    }

    def read(value: JsValue) = value match {
      case JsNumber(n) => n.intValue()
      case JsString(s) => s
      case JsTrue => true
      case JsFalse => false
    }
  }

  implicit val publicItemFmt = jsonFormat2(ImageResponse)
  implicit val publicItemSummaryFmt = jsonFormat3(ImageRequest)

}
