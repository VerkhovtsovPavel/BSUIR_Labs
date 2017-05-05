trait Service extends HttpService with ServiceJsonProtocol {
  ...
  def route(model: ActorRef)(implicit askTimeout: Timeout) =
    authorizeToken(verifyNotExpired && injectUser) {
      user =>
        get {
            path("sample" / IntNumber) { id =>
              onSuccess(model ? id) {
                case item: Sample =>
                  if(item.userId = user.uid)
                    complete(OK, item)
                  else
                    complete(Forbidden, "Access denided")    
                case ItemNotFound =>
                  complete(NotFound, "Not Found")
              }
            }
        } ~
        post {
          path("newSample") {
            entity(as[ImageRequest]) {
              image =>
                onSuccess(model ? (image, user.uid)) {
                  case id: Int =>
                    complete(OK, id.toString)
                }
  ...    
}