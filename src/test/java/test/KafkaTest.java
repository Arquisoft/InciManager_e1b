package test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import asw.Application;
import asw.entities.Incidence;
import asw.kafkaManager.KafkaService;


@SpringBootTest(classes = { Application.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class KafkaTest {
	
	@Autowired
	KafkaService kafkaService;
	
	private Incidence incidence;
	
	@Before
	public void setUp() {
		
		String username = "entidad2";
		String password = "123456";

		String name= "FUGA GAS";
		String description = "Fuga de gas cocina";
		String location = "30,30";
		List<String> tags = new LinkedList<String>();
		tags.add("gas");
		tags.add("fuga");
		String additionalInformation ="Butano";
		Map<String, String> properties = new HashMap<String,String>();
		properties.put("Bombona", "butano");
		String state="Abierta";
		String notification = "si";
		String expireAt = "2018-10-25 10:03:29.76579";
		String assignedTo = "oper_Glh";
		
		incidence = new Incidence(username, password, name, description, location,
				 tags, additionalInformation, properties,  state,
				 notification,  expireAt,  assignedTo);
		
	}
	
	@Test
	public void testKafka() {
		kafkaService.sendInci(incidence);
	}
}

