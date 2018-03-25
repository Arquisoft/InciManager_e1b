package asw.restService;

import java.util.Map;

//Class extracted from: https://github.com/Arquisoft/InciManager_e1b/blob/develop/src/main/java/asw/restService/AgentLoginFormatter.java
public class AgentLoginFormatter {
	private static final String BASE_QUERY = "{\"ident\":\"%s\", \"password\":\"%s\", \"kind\":\"%s\"}";

	private String userName, password, kind;

	public AgentLoginFormatter( String userName, String password, String kind ) {
		this.userName = userName;
		this.password = password;
		this.kind = kind;
	}

	public AgentLoginFormatter( Map<String, Object> payload ) {
		this( (String) payload.get( "ident" ),
			  (String) payload.get( "password" ),
			  Integer.toString( (int) payload.get( "kind" ))
			);
	}

	public String query() {
		return String.format( BASE_QUERY, this.userName, this.password, this.kind );
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}
	
	
}
