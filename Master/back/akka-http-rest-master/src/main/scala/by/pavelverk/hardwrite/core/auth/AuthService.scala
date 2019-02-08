package by.pavelverk.hardwrite.core.auth

import java.util.UUID

import com.roundeights.hasher.Implicits._
import io.circe.generic.auto._
import io.circe.syntax._
import by.pavelverk.hardwrite.core.{AuthData, AuthToken, AuthTokenContent, UserId}
import by.pavelverk.hardwrite.utils.MonadTransformers._
import pdi.jwt.{Jwt, JwtAlgorithm}

import scala.concurrent.{ExecutionContext, Future}

class AuthService(
  authDataStorage: AuthDataStorage,
  secretKey: String
)(implicit executionContext: ExecutionContext) {

  def signIn(login: String, password: String): Future[Option[AuthToken]] =
    authDataStorage
      .findAuthData(login)
      .filterT(_.password == password.sha256.hex)
      .mapT(authData => encodeToken(authData.id))

  def signUp(login: String, email: String, password: String): Future[AuthToken] =
    authDataStorage
      .saveAuthData(AuthData(UUID.randomUUID().toString, login, email, password.sha256.hex))
      .map(authData => encodeToken(authData.id))

  private def encodeToken(userId: UserId): AuthToken =
    Jwt.encode(AuthTokenContent(userId).asJson.noSpaces, secretKey, JwtAlgorithm.HS256)

}