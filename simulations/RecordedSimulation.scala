
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8090")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (X11; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0")

	val headers_0 = Map("Upgrade-Insecure-Requests" -> "1")

	val headers_1 = Map("Accept" -> "text/css,*/*;q=0.1")

	val headers_2 = Map("Accept" -> "*/*")



	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/")
			.headers(headers_0)
			.resources(http("request_1")
			.get("/css/custom.css")
			.headers(headers_1),
            http("request_2")
			.get("/img/student-48.png")
			.headers(headers_2)))
		.pause(1)
		.exec(http("request_3")
			.get("/login")
			.headers(headers_0)
			.resources(http("request_4")
			.get("/css/custom.css")
			.headers(headers_1),
            http("request_5")
			.get("/img/student-48.png")
			.headers(headers_2)))
		.pause(7)
		.exec(http("request_6")
			.post("/login")
			.headers(headers_0)
			.formParam("ident", "12345678P")
			.formParam("password", "123456")
			.formParam("kind", "1")
			.resources(http("request_7")
			.get("/css/custom.css")
			.headers(headers_1),
            http("request_8")
			.get("/img/student-48.png")
			.headers(headers_2)))
		.pause(58)
		.exec(http("request_9")
			.post("/sendIncident")
			.headers(headers_0)
			.formParam("name", "inc_AAAA")
			.formParam("description", "lectura nueva de humedad")
			.formParam("location", "2919,1514")
			.formParam("tags", "nieve,fuego")
			.formParam("additionalInformation", "www.google.es")
			.formParam("properties", "p0:v0,p1:v1")
			.formParam("state", "Abierta")
			.formParam("notification", "Si")
			.formParam("expiration", "2018-05-05")
			.formParam("assignedTo", "oper_1")
			.resources(http("request_10")
			.get("/css/custom.css")
			.headers(headers_1),
            http("request_11")
			.get("/img/student-48.png")
			.headers(headers_2)))


	setUp(scn.inject(atOnceUsers(1000))).protocols(httpProtocol)

/**
https://gatling.io/docs/2.3/general/simulation_setup/
*/
}
