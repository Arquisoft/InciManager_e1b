package test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import asw.Application;
import asw.webService.IncidenceData;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	private IncidenceData incidenceData1;
	private IncidenceData incidenceData2;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();

		String username = "entidad2";
		String password = "123456";

		String name = "FUGA GAS";
		String description = "Fuga de gas cocina";
		String location = "29,29";
		String tags = "bombona,gas";
		String additionalInformation = "Butano";
		String properties = "bombona:butano";
		String state = "Abierta";
		String notification = "Si";
		String expireAt = "2018-10-25 10:02:29.769579";
		String assignedTo = "oper_rUxl";

		incidenceData1 = new IncidenceData(username, password, name, description, location, tags, additionalInformation,
				properties, state, notification, expireAt, assignedTo);

		username = "entidad2";
		password = "123456";

		name = "Incendio";
		description = "Incendio muy grande";
		location = "-90,90";
		tags = "asd,edf";
		state = "Abierta";
		notification = "Si";
		expireAt = "2018-10-25 10:02:29.769579";
		assignedTo = "oper_rUxl";

		incidenceData2 = new IncidenceData(username, password, name, description, location, tags, additionalInformation,
				properties, state, notification, expireAt, assignedTo);

	}

	@Test
	public void T1emptyIncidentName() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Incidence name is required\"}";

		incidenceData1.setName("");
		incidenceData2.setName("");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

	}

	@Test
	public void T2emptyIncidentDescription() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Incidence description is required\"}";

		incidenceData1.setDescription("");
		incidenceData2.setDescription("");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

	}

	@Test
	public void T3emptyIncidentTags() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Incidence tags is required\"}";

		incidenceData1.setTags("");
		incidenceData2.setTags("");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

	}

	@Test
	public void T4emptyIncidentInformation() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Incidence additional information is required\"}";

		incidenceData1.setAdditionalInformation("");
		incidenceData2.setAdditionalInformation("");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

	}

	@Test
	public void T5emptyIncidentProperties() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Incidence properties is required\"}";

		incidenceData1.setProperties("");
		incidenceData2.setProperties("");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

	}

	@Test
	public void T6emptyIncidentState() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Incidence state is required\"}";

		incidenceData1.setState("");
		incidenceData2.setState("");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));
	}

	@Test
	public void T7emptyIncidentNotification() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Incidence notification is required\"}";

		incidenceData1.setNotification("");
		incidenceData2.setNotification("");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));
	}

	@Test
	public void T8emptyIncidentExpiration() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Incidence expiration is required\"}";

		incidenceData1.setExpiration("");
		incidenceData2.setExpiration("");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));
	}

	@Test
	public void T9emptyIncidentAssignedTo() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Incidence assigned to is required\"}";

		incidenceData1.setAssignedTo("");
		incidenceData2.setAssignedTo("");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));
	}

	@Test
	public void T10worngTagsStyle() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Wrong tags style\"}";

		incidenceData1.setTags("xsd , dxxs, xs");
		incidenceData2.setTags("xsd dxxs, xs");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));
	}

	@Test
	public void T11worngPropertiesStyle() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Wrong properties style\"}";

		incidenceData1.setProperties("cdsc,vale:xsa");
		incidenceData2.setProperties("xas,xsx ,val:sxa");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));
	}

	@Test
	public void T12worngLocationStyle() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String emptyName = "{\"reason\": \"Wrong location style\"}";

		incidenceData1.setLocation("93.21,-55.66");
		incidenceData2.setLocation("-66.56,90.33");

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(emptyName));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(emptyName));
	}

	@Test
	public void T13AcceptIncident() {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String incidenceURI = base.toString() + "/postIncident";
		String request1 = "{\"username\":null,\"password\":null,\"name\":\"FUGA GAS\",\"description\":\"Fuga de gas cocina\",\"location\":\"29,29\",\"tags\":\"bombona,gas\",\"additionalInformation\":\"Butano\",\"properties\":\"bombona:butano\",\"state\":\"Abierta\",\"notification\":\"Si\",\"expiration\":\"2018-10-25 10:02:29.769579\",\"assignedTo\":\"oper_rUxl\"}";
		String request2 = "{\"username\":null,\"password\":null,\"name\":\"Incendio\",\"description\":\"Incendio muy grande\",\"location\":\"-90,90\",\"tags\":\"asd,edf\",\"additionalInformation\":\"Butano\",\"properties\":\"bombona:butano\",\"state\":\"Abierta\",\"notification\":\"Si\",\"expiration\":\"2018-10-25 10:02:29.769579\",\"assignedTo\":\"oper_rUxl\"}";

		response = template.postForEntity(incidenceURI, incidenceData1, String.class);
		assertThat(response.getBody(), equalTo(request1));

		response = template.postForEntity(incidenceURI, incidenceData2, String.class);
		assertThat(response.getBody(), equalTo(request2));
	}

}
