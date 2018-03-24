package asw.restService;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;

@RestController
public class RESTController {
	
	@Autowired
	AgentsConnector agentsConnector;
	
	@RequestMapping(value = "/incidence-creator", method = RequestMethod.POST, headers = { "Accept=application/json",
	"Accept=application/xml" }, produces = { "application/json", "text/xml" })
	public ResponseEntity<String> processIncidence(@RequestBody Map<String, Object> params) {
		@SuppressWarnings("rawtypes")
		HttpResponse auth = agentsConnector.executeQuery( new AgentLoginFormatter(params).query() );
		
		if(auth.getStatus() != HttpStatus.OK.value()) {
			return new ResponseEntity<String>("{\"response\":\"Login incorrecto\"}", HttpStatus.UNAUTHORIZED );
		}
		
		System.out.println(auth.getStatus());
		
		//Todavia no hay incidencia que crear, pero el login es correcto
		//con lo cual ya esta comunicado con el módulo de agents
		return new ResponseEntity<String>("{\"response\":\"Incidencia procesada\"}", HttpStatus.OK );

		
	}
}
