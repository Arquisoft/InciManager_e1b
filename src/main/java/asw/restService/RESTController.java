package asw.restService;

import java.io.IOException;


import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import asw.entities.Incidence;
import asw.kafkaManager.KafkaServiceImpl;

@RestController
public class RESTController {
	
	@Autowired
	AgentsConnector agentsConnector;
	
	@Autowired
	KafkaServiceImpl kafkaManager;

	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/incidence-creator", method = RequestMethod.POST, headers = { "Accept=application/json",
	"Accept=application/xml" }, produces = { "application/json", "text/xml" })
	public ResponseEntity<String> processIncidence(@RequestBody Map<String, Object> params) {

		HttpResponse auth;
		try {
			auth = agentsConnector.launchRequest( new AgentLoginFormatter(params).getLoginAsJSON() );
			if(auth.getStatusLine().getStatusCode() != HttpStatus.OK.value()) {
				return new ResponseEntity<String>("{\"response\":\"Login incorrecto\"}", HttpStatus.UNAUTHORIZED );
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		Incidence incidence = new Incidence();

		incidence.setUsername((String) params.get("ident"));
		incidence.setPassword((String) params.get("password"));
		incidence.setName((String) params.get("name"));
		incidence.setDescription((String) params.get("description"));
		incidence.setLocation((String) params.get("location"));

		incidence.setTags((List<String>) params.get("tags"));
		incidence.setAdditionalInformation((String) params.get("additionalInformation"));
		incidence.setProperties((Map<String, String>) params.get("properties"));

		incidence.setState((String) params.get("state"));
		incidence.setNotification((String) params.get("notification"));
		incidence.setExpiration((String) params.get("expireAt"));
		incidence.setAssignedTo((String) params.get("assignedTo"));
		
		kafkaManager.sendInci(incidence);
		
		//Enviar el objeto con la informacion de la incidencia a kafka y al mongo
		return new ResponseEntity<String>("{\"response\":\"Incidencia procesada\"}", HttpStatus.OK );

		
	}
}
