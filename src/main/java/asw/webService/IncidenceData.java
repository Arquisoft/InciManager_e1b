package asw.webService;

public class IncidenceData {

	private String username;
	private String password;
	private String name;
	private String description;
	private String location;
	private String tags;
	private String additionalInformation;
	private String properties;
	private String state;
	private String notification;
	private String expiration;
	private String assignedTo;
	
	
	public IncidenceData() {
		// TODO Auto-generated constructor stub
	}

	public IncidenceData(String username, String password, String name, String description, String location,
			String tags, String additionalInformation, String properties, String state, String notification,
			String expiration, String assignedTo) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.description = description;
		this.location = location;
		this.tags = tags;
		this.additionalInformation = additionalInformation;
		this.properties = properties;
		this.state = state;
		this.notification = notification;
		this.expiration = expiration;
		this.assignedTo = assignedTo;
	}
	
	
	public IncidenceData(IncidenceData incidenceData) {
		super();
		this.username = incidenceData.getUsername();
		this.password = incidenceData.getPassword();
		this.name = incidenceData.getName();
		this.description = incidenceData.getDescription();
		this.location = incidenceData.getLocation();
		this.tags = incidenceData.getTags();
		this.additionalInformation = incidenceData.getAdditionalInformation();
		this.properties = incidenceData.getProperties();
		this.state = incidenceData.getState();
		this.notification = incidenceData.getNotification();
		this.expiration = incidenceData.getExpiration();
		this.assignedTo = incidenceData.getAssignedTo();
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public String getTags() {
		return tags;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public String getProperties() {
		return properties;
	}

	public String getState() {
		return state;
	}

	public String getExpiration() {
		return expiration;
	}

	public String getAssignedTo() {
		return assignedTo;
	}
	
	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

}
