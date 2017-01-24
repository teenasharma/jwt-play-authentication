package controllers

import javax.inject._

import play.api.mvc._

@Singleton
class HomeController @Inject()(securedAuthenticator: SecuredAuthenticator) extends Controller {

  def index = securedAuthenticator.JWTAuthentication { implicit request =>
    Ok(views.html.index("Your new application is ready."))
  }
}

