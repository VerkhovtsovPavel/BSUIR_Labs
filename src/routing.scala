trait Service extends HttpService with ServiceJsonProtocol {
  import scala.language.postfixOps
  implicit def ec = actorRefFactory.dispatcher
  def route(model: ActorRef)(implicit askTimeout: Timeout) =
    get {
      path("sample") {
        onSuccess(model ? 'list) {
          case item: List[Samples] =>
            complete(OK, item)
        }
      } ~
        path("sample" / IntNumber) { id =>
          onSuccess(model ? id) {
            case item: Sample =>
              complete(OK, item)
            case ItemNotFound =>
              complete(NotFound, "Not Found")
          }
        }
    } ~
    post {
      path("newSample") {
        entity(as[ImageRequest]) {
          image =>
            onSuccess(model ? image) {
              case id: Int =>
                complete(OK, id.toString)
            }
        }
      }
    } ~
    detete {
      path("sample" / IntNumber) { id =>
          onSuccess(model ? id) {
            case item: Sample =>
              complete(OK, item)
            case ItemNotFound =>
              complete(NotFound, "Not Found")
          }
      }
    } ~
    put {
      path("sample" / IntNumber) {
        entity(as[ImageRequest]) {
          image =>
            onSuccess(model ? image) {
              case item: Sample =>
                complete(OK, item)
              case ItemNotFound =>
                complete(NotFound, "Not Found")
          }
        }
      }
    }
}