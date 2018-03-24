package asw.restService;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class AgentsConnector {
	private static final String URL = "http://localhost:8091/user";
	
	public HttpResponse<JsonNode> executeQuery(String query) {
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.post( URL )
					.header( "Content-Type", "application/json" )
					.body( query )
					.asJson();
			return jsonResponse;
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
