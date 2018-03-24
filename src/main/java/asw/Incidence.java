package asw;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Incidence {
	
	@Id
	private ObjectId _id;

	private String username;
	private String password;

	private String name;
	private String description;
	private String location;
	private List<String> tags;
	private String additionalInformation;
	private Map<String, String> properties;
	private String state="OPEN";
	private String notification;
	private String expireAt;
	private String assignedTo;

	public Incidence() {}

	
	public Incidence(String username, String password, String name, String description, String location,
			List<String> tags, String additionalInformation, Map<String, String> properties, String state,
			String notification, String expireAt, String assignedTo) {
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
		this.expireAt = expireAt;
		this.assignedTo = assignedTo;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getExpiration() {
		return expireAt;
	}

	public void setExpiration(String expiration) {
		this.expireAt = expiration;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;


	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	

	

}

