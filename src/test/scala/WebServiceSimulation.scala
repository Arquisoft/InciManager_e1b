
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class WebServiceSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8090")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (X11; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_8 = Map("Pragma" -> "no-cache")

    val uri2 = "http://detectportal.firefox.com/success.txt"

	val scn = scenario("WebServiceSimulation")
		.exec(http("request_0")
			.get("/login")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/js/genAndFill.js"),
            http("request_2")
			.get("/css/custom.css")
			.headers(headers_2),
            http("request_3")
			.get("/img/student-48.png")))
		.pause(5)
		.exec(http("request_4")
			.post("/login")
			.headers(headers_0)
			.formParam("ident", "12345678P")
			.formParam("password", "123456")
			.formParam("kind", "1")
			.resources(http("request_5")
			.get("/js/genAndFill.js"),
            http("request_6")
			.get("/css/custom.css")
			.headers(headers_2),
            http("request_7")
			.get("/img/student-48.png")))
		.pause(2)
		.exec(http("request_8")
			.get(uri2 + "")
			.headers(headers_8))
		.pause(1)
		.exec(http("request_9")
			.post("/sendIncident")
			.headers(headers_0)
			.formParam("name", "inc_h5sv4")
			.formParam("description", "Nueva lectura de la temperatura")
			.formParam("location", "24.7565,44.8966")
			.formParam("tags", "Niebla")
			.formParam("additionalInformation", "http://puntoverdeleon.com.mx/wp-content/uploads/2016/09/imagen-de-prueba-320x240.jpg")
			.formParam("properties", "p0:v0,p1:v1,p2:v2,p3:v3")
			.formParam("state", "Abierta")
			.formParam("notification", "Si")
			.formParam("expiration", "2019-05-15 10:02:29.769579")
			.formParam("assignedTo", "oper_zchsv")
			.formParam("btn", "")
			.resources(http("request_10")
			.get("/js/genAndFill.js"),
            http("request_11")
			.get("/css/custom.css")
			.headers(headers_2),
            http("request_12")
			.get("/img/student-48.png")))
		.pause(2)
		.exec(http("request_13")
			.get("/logout")
			.headers(headers_0)
			.resources(http("request_14")
			.get("/css/custom.css")
			.headers(headers_2),
            http("request_15")
			.get("/js/genAndFill.js"),
            http("request_16")
			.get("/img/student-48.png")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
