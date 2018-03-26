package asw.webService;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import asw.Incidence;
import asw.kafkaManager.KafkaServiceImpl;
import asw.restService.AgentLoginFormatter;

import asw.restService.AgentsConnector;
import asw.webService.errors.ErrorResponse;

@Controller
public class WebController {

	@Autowired
	AgentsConnector agentsConnector;

	@Autowired
	KafkaServiceImpl kafkaManager;

	@RequestMapping(value = "/")
	public String index() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fillLogin(@RequestParam String ident, @RequestParam String password, @RequestParam String kind) {
		HttpResponse auth;
		try {
			auth = agentsConnector.launchRequest(new AgentLoginFormatter(ident, password, kind).getLoginAsJSON());
			if (auth.getStatusLine().getStatusCode()== HttpStatus.OK.value()) {
				agentsConnector.setUsername(ident);
				agentsConnector.setPassword(password);
				String response = EntityUtils.toString(auth.getEntity(), "UTF-8");
				JSONObject jsonData = new JSONObject(response);
				agentsConnector.setLocation(jsonData.getString("location"));
				return "incidentForm";
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		return "login";
	}

	@RequestMapping(value = "/incident")
	public String getIncident() {
		return "incidentDetails";
	}

	@RequestMapping(value = "/sendIncident", method = RequestMethod.POST)
	public String createIncident(Model model, @ModelAttribute IncidenceData incidenceData) {

		Incidence incidence = new Incidence();

		incidence.setUsername(agentsConnector.getUsername());
		incidence.setPassword(agentsConnector.getPassword());
		incidence.setName(incidenceData.getName());
		incidence.setDescription(incidenceData.getDescription());
		
		if (incidenceData.getLocation() == "") {
			incidence.setLocation(agentsConnector.getLocation());
		}
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

		model.addAttribute("incidence", incidenceData);

		return "incidentDetails";
	}

	@RequestMapping(value = "/resendIncident", method = RequestMethod.POST)
	public String resendIncident() {
		return "incidentForm";
	}

	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound(ErrorResponse excep, Model model) {
		model.addAttribute("error", excep.getMessageStringFormat());

		return "error";
	}

}