package asw.restService;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

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
		
		//Añadimos los datos del agente a la incidencia
		Map jsonContent = (Map) params.get("incidencia");
		jsonContent.put("name", auth.getBody().getObject().get("name"));
		jsonContent.put("location", auth.getBody().getObject().get("location"));
		jsonContent.put("email", auth.getBody().getObject().get("email"));
		jsonContent.put("id", auth.getBody().getObject().get("id"));
		jsonContent.put("kind", auth.getBody().getObject().get("kind"));
		jsonContent.put("kindCode", auth.getBody().getObject().get("kindCode"));
		
		System.out.println(new JSONObject(jsonContent));
		
		//Enviar el objeto con la informacion de la incidencia a kafka y al mongo
		return new ResponseEntity<String>("{\"response\":\"Incidencia procesada\"}", HttpStatus.OK );

		
	}
}
