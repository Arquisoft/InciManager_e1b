package asw.kafka;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.ManagedBean;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.jboss.logging.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import asw.Incidence;
import asw.dbManagement.MongoDatabase;

@ManagedBean
public class KafkaServiceImpl implements KafkaService {
	
	@Value("${kafka.topic}")
	private String TOPIC;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private MongoDatabase mongoDatabase;
	
	private static final Logger logger = Logger.getLogger(KafkaProducer.class);
	private Incidence inci;

	@Override
	public void sendInci(Incidence incidence) {
		this.inci = incidence;
		
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("username", incidence.getUsername());
		map.put("name", incidence.getName());
		map.put("description", incidence.getDescription());
		map.put("location", incidence.getLocation());
		map.put("tags", incidence.getTags());
		map.put("multimedia", incidence.getAdditionalInformation());
		map.put("properties", incidence.getProperties());
		map.put("state", incidence.getState());
		map.put("notification", incidence.getNotification());
		map.put("expireAt", incidence.getExpiration());
		map.put("assignedTo", incidence.getAssignedTo());
		
		
		send(TOPIC, new JSONObject(map).toString());

	}

	private void send(String topic, String data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("SUCCESS on sending message \"" + data + "\" to topic " + topic);
				mongoDatabase.sendInci(inci);
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("ERROR on sending message \"" + data + "\", stacktrace " + ex.getMessage());
			}
		});
	}

}
