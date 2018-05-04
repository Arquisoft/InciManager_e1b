package asw.restService;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;



@Service
public class AgentsConnector {
	private static final String URL = "https://mighty-peak-58763.herokuapp.com/user";

	private String username;
	private String password;
	private String location;

	
	public HttpResponse launchRequest(String query) throws ClientProtocolException, IOException {
		StringEntity entity = new StringEntity(query, ContentType.APPLICATION_JSON);
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpPost req = new HttpPost(URL);
		req.setEntity(entity);
		HttpResponse response = httpClient.execute(req);
		return response;
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