package asw.restService;

import java.util.Map;

//Class extracted from: https://github.com/Arquisoft/InciManager_i3a/blob/master/src/main/java/org/uniovi/i3a/incimanager/rest/AgentsQueryFormatter.java
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
