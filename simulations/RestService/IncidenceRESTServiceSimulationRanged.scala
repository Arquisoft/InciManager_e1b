import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._


/**
	Tutorial probando REST API: https://www.blazemeter.com/blog/api-load-testing-with-gatling
	Se intentaran crear entre 500 y 1000 incidencias a lo largo de 20 segundos
*/

class IncidenceRESTServiceSimulationRanged extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost:8090")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.contentTypeHeader("application/json")
		.userAgentHeader("curl/7.54.0")

	val scn = scenario("IncidenceRESTServiceSimulationRanged")
		.exec(http("request_0")
			.post("/incidence-creator")
			.body(StringBody(""" {"ident":"entidad2","password":"123456","kind":2,"name":"inc_GHKB","description":"Nueva lectura de humedad","location":"2919,178","tags":["Nieve","Fuego","Niebla","Terremoto"],"additionalInformation":"http://puntoverdeleon.com.mx/wp-content/uploads/2016/09/imagen-de-prueba-320x240.jpg","properties":{"p0":"v0","p1":"v1","p2":"v2","p3":"v3"},"state":"Abierta","notification":"si","expireAt":"2018-10-25 10:02:29.769579","assignedTo":"oper_rUxl"} """)).asJSON
		)

	setUp(scn.inject(rampUsersPerSec(500) to (1000) during(20 seconds))).protocols(httpProtocol)
}
