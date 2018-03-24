package asw.restService;

import java.util.Map;

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
}
