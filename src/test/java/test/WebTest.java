package test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import asw.Application;
import asw.webService.IncidenceData;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebTest {

	private IncidenceData incidenceData1;

	private MockHttpSession session;
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() throws Exception {

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

		mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

	}

	@Test
	public void T14acceptWebLogin() throws Exception {
		MockHttpServletRequestBuilder request = post("/login").session(session).param("ident", "12345678P")
				.param("password", "123456").param("kind", "1");
		mockMvc.perform(request).andExpect(status().isOk());

	}

	@Test
	public void T15wrongTagsWebStyle() throws Exception {
		MockHttpServletRequestBuilder request = post("/sendIncident").param("username", incidenceData1.getUsername())
				.param("password", incidenceData1.getPassword()).param("name", incidenceData1.getName())
				.param("description", incidenceData1.getDescription()).param("location", incidenceData1.getLocation())
				.param("tags", incidenceData1.getTags().toString())
				.param("additionalInformation", incidenceData1.getAdditionalInformation())
				.param("properties", incidenceData1.getProperties().toString())
				.param("state", incidenceData1.getState()).param("notification", incidenceData1.getNotification())
				.param("expiration", incidenceData1.getExpiration())
				.param("assignedTo", incidenceData1.getAssignedTo());

		mockMvc.perform(request).andExpect(status().isOk());

	}

}
