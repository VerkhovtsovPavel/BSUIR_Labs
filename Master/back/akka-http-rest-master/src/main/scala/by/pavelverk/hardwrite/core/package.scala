package by.pavelverk.hardwrite

package object core {

  type UserId = String
  type SampleId = String
  type FeaturesId = String
  type AuthToken = String

  final case class AuthTokenContent(userId: UserId)

  final case class AuthData(id: UserId, username: String, email: String, password: String) {
    require(id.nonEmpty, "id.empty")
    require(username.nonEmpty, "username.empty")
    require(email.nonEmpty, "email.empty")
    require(password.nonEmpty, "password.empty")
  }

  final case class UserProfile(id: UserId, firstName: String, lastName: String) {
    require(id.nonEmpty, "id.empty")
    require(firstName.nonEmpty, "firstName.empty")
    require(lastName.nonEmpty, "lastName.empty")
  }

  final case class UserProfileUpdate(firstName: Option[String] = None, lastName: Option[String] = None) {
    def merge(profile: UserProfile): UserProfile = {
      UserProfile(profile.id, firstName.getOrElse(profile.firstName), lastName.getOrElse(profile.lastName))
    }
  }

  final case class Sample(id: SampleId, user_id: UserId, times: List[Double], x: List[Double], y: List[Double], e: List[Double]) {
    require(id.nonEmpty, "id.empty")
  }

  final case class Features(id: FeaturesId, sample_id: SampleId, timer: Double, lines: Int, square: Double, horizontalLength: Int, verticalLength: Int, totalLength: Int, maxVelocity: Double, minVelocity: Double, durationX: Double, durationY: Double, characterTilt: Int, lineTilt: Int, characterSpacing: Double, wordSpacing: Double, lineSpacing: Double, frequencyOfText: Double) {
    require(id.nonEmpty, "id.empty")
  }

  final case class Result(id: SampleId, neuro_results: Double, psicho_result: Int){
    require(id.nonEmpty, "id.empty")
  }
}

