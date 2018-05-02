
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
		Assert.isIncidentDescriptionEmpty(incidenceData.getDescription());
		Assert.isIncidentTagsEmpty(incidenceData.getTags());
		Assert.isIncidentInfomationEmpty(incidenceData.getAdditionalInformation());
		Assert.isIncidentPropertiesEmpty(incidenceData.getProperties());
		Assert.isIncidentStateEmpty(incidenceData.getState());
		Assert.isIncidentNotificationEmpty(incidenceData.getNotification());
		Assert.isIncidentExpirationEmpty(incidenceData.getExpiration());
		Assert.isIncidentAssignedToEmpty(incidenceData.getAssignedTo());

		Assert.areTagsValid(incidenceData.getTags());
		Assert.arePropertiesValid(incidenceData.getProperties());
		if (incidenceData.getLocation() != "") {
            Assert.isLocationValid(incidenceData.getLocation());
        }

		Incidence incidence = new Incidence();

		String username = agentsConnector.getUsername();
		String password = agentsConnector.getPassword();
		String name = incidenceData.getName();
		String description = incidenceData.getDescription();
		String location;
		String tagsS = incidenceData.getTags();
		String aI = incidenceData.getAdditionalInformation();
		String propertiesS = incidenceData.getProperties();
		String state = incidenceData.getState();
		String noti = incidenceData.getNotification();
		String exp = incidenceData.getExpiration();
		String assig = incidenceData.getAssignedTo();

		incidence.setUsername(username);
		incidence.setPassword(password);
		incidence.setName(name);
		incidence.setDescription(description);

		if (incidenceData.getLocation() == "") {
			incidence.setLocation(agentsConnector.getLocation());
			location = agentsConnector.getLocation();
		} else {
			incidence.setLocation(incidenceData.getLocation());
			location = incidenceData.getLocation();
		}

		List<String> tags = new ArrayList<String>();
		for (String tag : ((String) tagsS).split(",")) {
			tags.add(tag.trim());
		}
		incidence.setTags(tags);

		incidence.setAdditionalInformation(aI);

		Map<String, String> properties = new HashMap<String, String>();
		for (String property : ((String) propertiesS).split(",")) {
			if (property.split(":").length == 2)
				properties.put(property.split(":")[0].trim(), property.split(":")[1].trim());
		}
		incidence.setProperties(properties);

		incidence.setState(state);
		incidence.setNotification(noti);
		incidence.setExpiration(exp);
		incidence.setAssignedTo(assig);

		kafkaManager.sendInci(incidence);

		return new ResponseEntity<IncidenceData>(new IncidenceData(username, password, name, description, location,
				tagsS, aI, propertiesS, state, noti, exp, assig), HttpStatus.OK);
	}

	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponses(ErrorResponse error) {
		return error.getMessageJSONFormat();
	}

}
