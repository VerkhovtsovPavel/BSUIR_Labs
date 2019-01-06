  val route: Route = pathPrefix("samples") {
    pathPrefix("new") {
      pathEndOrSingleSlash {
        authenticate(secretKey) { userId =>
          post {
            entity(as[Sample]) { sample =>
              complete(createSample(sample).map {
                case s: Sample =>
                  OK -> s.asJson
                case _ =>
                  BadRequest -> None.asJson
              })
            }
          }
        }
      }
    } ~
      pathPrefix(Segment) { id =>
        pathEndOrSingleSlash {
          get {
            complete(getSample(id).map {
              case Some(sample) =>
                OK -> sample.asJson
              case None =>
                BadRequest -> None.asJson
            })
          }
        }
      }