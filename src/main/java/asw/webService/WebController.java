package asw.webService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import asw.restService.AgentLoginFormatter;
import asw.restService.AgentsConnector;
import asw.webService.errors.ErrorResponse;

@Controller
public class WebController {
	
	@Autowired
	AgentsConnector agentsConnector;
	
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
		return "incidentCreationForm";
	}
	
	@RequestMapping(value = "/create-incident")
	public String createIncident() {
		return "incidentCreationForm";
	}
	
	@ExceptionHandler(ErrorResponse.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String handleErrorResponseNotFound(ErrorResponse excep, Model model) {
		model.addAttribute("error", excep.getMessageStringFormat());

		return "error";
	}
}
