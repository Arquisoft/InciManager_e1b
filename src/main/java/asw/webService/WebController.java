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
import asw.webService.errors.ErrorResponse;

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
			return "incidentCreationForm";
		
		return "login";
	}
	
	@RequestMapping(value = "/incident")
	public String getIncident() {
		return "incidentDetails";
	}
	
	@RequestMapping(value = "/sendIncident", method = RequestMethod.POST)
	public String createIncident(Model model, @ModelAttribute Incidence incidence) {
		mongoDatabase.sendInci(incidence);
		
		model.addAttribute("username", incidence.getUsername());
		model.addAttribute("name", incidence.getName());
		model.addAttribute("description", incidence.getDescription());
		model.addAttribute("location", incidence.getLocation());
		
		List<String> tags = new ArrayList<String>();
		for (String tag : ((String) incidence.getTags()).split(",")) {
			tags.add(tag.trim());
		}
		model.addAttribute("tags", tags);
		
		
		List<String> informationList = new ArrayList<String>();
		for (String information : ((String) incidence.getAdditionalInformation()).split(",")) {
			informationList.add(information.trim());
		}
		model.addAttribute("additionalInformation", informationList);
		
		Map<String, String> properties = new HashMap<String, String>();
		for (String property : ((String) incidence.getProperties()).split(",")) {
			if (property.split(":").length == 2)
				properties.put(property.split(":")[0].trim(), property.split(":")[1].trim());
		}
		model.addAttribute("properties", properties);
		
		model.addAttribute("state", incidence.getState());
		model.addAttribute("expiration", incidence.getExpiration());
		model.addAttribute("assignedTo", incidence.getAssignedTo());
		
		return "incidentDetails";
	}
	
	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound(ErrorResponse excep, Model model) {
		model.addAttribute("error", excep.getMessageStringFormat());

		return "error";
	}
}
