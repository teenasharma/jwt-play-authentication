package controllers

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc._
import utilities.JwtUtility

import scala.concurrent.Future

case class User(email: String, userId: String)

case class SecuredRequest[A](user: User, request: Request[A]) extends WrappedRequest(request)

class SecuredAuthenticator @Inject()(jwtHelper: JwtUtility) extends Controller {

  implicit val formatUserDetails = Json.format[User]

  object JWTAuthentication extends ActionBuilder[SecuredRequest] {
    def invokeBlock[A](request: Request[A], block: (SecuredRequest[A]) => Future[Result]): Future[Result] = {
      implicit val req = request

      val jwtToken = request.headers.get("jwt_token").getOrElse("")

      if (jwtHelper.isValidToken(jwtToken)) {
        jwtHelper.decodePayload(jwtToken).fold {
          Future.successful(Unauthorized("Invalid credential"))
        } { payload =>
          val userInfo = Json.parse(payload).validate[User].get

          if (userInfo.email == "test@example.com" && userInfo.userId == "12345") {
            Future.successful(Ok("Authorization successful"))
          } else {
            Future.successful(Unauthorized("Invalid credential"))
          }
        }
      }
      else {
        Future.successful(Unauthorized("Invalid credential"))
      }
    }
  }

}
