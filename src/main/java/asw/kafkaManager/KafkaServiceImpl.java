package asw.kafkaManager;


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

import asw.dbManagement.MongoDatabase;
import asw.entities.Incidence;

@ManagedBean
public class KafkaServiceImpl implements KafkaService {

	@Value("${kafka.topic}")
	private String TOPIC;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private MongoDatabase mongoDatabase;

	private static final Logger logger = Logger.getLogger(KafkaProducer.class);


	@Override
	public void sendInci(Incidence incidence) {
		
		send(TOPIC, incidence);

	}

	private void send(String topic, Incidence data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, new JSONObject(data).toString());
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("SUCCESS on sending message \"" + data + "\" to topic " + topic);
				mongoDatabase.sendInci(data);
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("ERROR on sending message \"" + data + "\", stacktrace " + ex.getMessage());
			}
		});
	}

}
