
// @GENERATOR:play-routes-compiler
// @SOURCE:/home/knoldus/workspace/jwt-play-authentication-demo/conf/routes
// @DATE:Tue Jan 24 13:33:04 IST 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
