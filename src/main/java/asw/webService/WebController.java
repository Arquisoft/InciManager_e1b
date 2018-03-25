package asw.webService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import asw.Incidence;
import asw.dbManagement.MongoDatabase;
import asw.restService.AgentLoginFormatter;

import asw.restService.AgentsConnector;

@Controller
public class WebController {
	
	@Autowired
	AgentsConnector agentsConnector;
	
	@Autowired
	MongoDatabase mongoDatabase;
	
	@RequestMapping(value = "/")
	public String index() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fillLogin(@RequestParam String ident, @RequestParam String password, @RequestParam String kind) {
		HttpResponse<JsonNode> auth = agentsConnector.executeQuery( new AgentLoginFormatter(ident, password, kind).query() );
		if(auth.getStatus() == HttpStatus.OK.value())
			return "incidentForm";
		
		return "login";
	}
	
	@RequestMapping(value = "/incident")
	public String getIncident() {
		return "incidentDetails";
	}
	
	
	@RequestMapping(value = "/sendIncident", method = RequestMethod.POST)
	public String createIncident(Model model, @ModelAttribute IncidenceData incidenceData) {
		Incidence incidence = new Incidence();
		
		incidence.setUsername(incidenceData.getUsername());
		incidence.setPassword(incidenceData.getPassword());
		incidence.setName(incidenceData.getName());
		incidence.setDescription(incidenceData.getDescription());
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
		incidence.setExpiration(incidenceData.getExpiration());
		incidence.setAssignedTo(incidenceData.getAssignedTo());
		
		mongoDatabase.sendInci(incidence);
		
		model.addAttribute("incidence", incidenceData);
		
		return "incidentForm";
	}


}
