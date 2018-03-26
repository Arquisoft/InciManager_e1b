package asw.restService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import asw.entities.Incidence;
import asw.kafkaManager.KafkaServiceImpl;
import asw.util.Assert;
import asw.webService.IncidenceData;
import asw.webService.errors.ErrorResponse;

@RestController
public class SendIncidentRESTController {

	@Autowired
	AgentsConnector agentsConnector;
	
	@Autowired
	KafkaServiceImpl kafkaManager;

	@RequestMapping(value = "/postIncident", method = RequestMethod.POST, headers = { "Accept=application/json",
			"Accept=application/xml" }, produces = { "application/json", "text/xml" })
	public ResponseEntity<IncidenceData> getPOSTIncident(@RequestBody(required = true) IncidenceData incidenceData) {

		Assert.isIncidentNameEmpty(incidenceData.getName());

		Incidence incidence = new Incidence();

		incidence.setUsername(agentsConnector.getUsername());
		incidence.setPassword(agentsConnector.getPassword());
		incidence.setName(incidenceData.getName());
		incidence.setDescription(incidenceData.getDescription());

		if (incidenceData.getLocation() == "")
			incidence.setLocation(agentsConnector.getLocation());
		else
			incidence.setLocation(incidenceData.getLocation());

		List<String> tags = new ArrayList<String>();
		for (String tag : ((String) incidenceData.getTags()).split(",")) {
			tags.add(tag.trim());
		}
		incidence.setTags(tags);

		incidence.setAdditionalInformation(incidenceData.getAdditionalInformation());

		Map<String, String> properties = new HashMap<String, String>();
		for (String property : ((String) incidenceData.getProperties()).split(",")) {
			if (property.split(":").length == 2)
				properties.put(property.split(":")[0].trim(), property.split(":")[1].trim());
		}
		incidence.setProperties(properties);

		incidence.setState(incidenceData.getState());
		incidence.setNotification(incidenceData.getNotification());
		incidence.setExpiration(incidenceData.getExpiration());
		incidence.setAssignedTo(incidenceData.getAssignedTo());

		kafkaManager.sendInci(incidence);
		return new ResponseEntity<IncidenceData>(new IncidenceData(incidenceData), HttpStatus.OK);
	}

	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponses(ErrorResponse error) {
		return error.getMessageJSONFormat();
	}

}
