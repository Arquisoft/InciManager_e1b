package asw.restService;

import java.util.Map;

public class AgentLoginFormatter {
	private String userName, password, kind;

	public AgentLoginFormatter( String userName, String password, String kind ) {
		this.userName = userName;
		this.password = password;
		this.kind = kind;
	}

	public AgentLoginFormatter( Map<String, Object> jsonData ) {
		this.userName = (String) jsonData.get("ident");
		this.password = (String) jsonData.get("password");
		this.kind = String.valueOf(jsonData.get("kind"));
	}

	public String getLoginAsJSON() {
		return String.format("{\"ident\":\"%s\", \"password\":\"%s\", \"kind\":\"%s\"}", this.userName, this.password, this.kind );
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	
}
