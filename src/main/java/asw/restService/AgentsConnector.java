package asw.restService;

import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class AgentsConnector {
	private static final String URL = "http://localhost:8091/user";

	private String username;
	private String password;
	private String location;

	public HttpResponse<JsonNode> executeQuery(String query) {
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.post(URL).header("Content-Type", "application/json")
					.body(query).asJson();
			return jsonResponse;
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}