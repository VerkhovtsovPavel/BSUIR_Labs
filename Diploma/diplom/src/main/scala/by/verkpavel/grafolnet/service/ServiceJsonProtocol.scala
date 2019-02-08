package by.verkpavel.grafolnet.service

import by.verkpavel.grafolnet.authorisation.AuthorisationData
import by.verkpavel.grafolnet.database.domain.{Sample, User}
import spray.json.{DefaultJsonProtocol, JsFalse, JsNumber, JsString, JsTrue, JsValue, JsonFormat}
import com.mongodb.casbah.Imports.ObjectId

trait ServiceJsonProtocol extends DefaultJsonProtocol {

  implicit object AnyJsonFormat extends JsonFormat[Any] {
    def write(x: Any) = x match {
      case n: Int => JsNumber(n)
      case s: String => JsString(s)
      case b: Boolean if b == true => JsTrue
      case b: Boolean if b == false => JsFalse
      case id: ObjectId => JsString("oid" + id.toHexString)
      case _ => JsString("Parse error")
    }

    def read(value: JsValue) = value match {
      case JsNumber(n) => n.intValue()
      case JsString(s) if s.startsWith("oid") =>
      case JsString(s) => s
      case JsTrue => true
      case JsFalse => false
    }
  }

  implicit object ObjectIDJsonFormat extends JsonFormat[ObjectId] {
    def write(x: ObjectId) = JsString(x.toHexString)

    def read(value: JsValue) = value match {
      case JsString(s) => new ObjectId(s)
    }
  }

  implicit val publicItemFmt = jsonFormat6(Sample)
  implicit val publicItemSummaryFmt = jsonFormat4(User)
  implicit val autDataFmt = jsonFormat2(AuthorisationData)

}
