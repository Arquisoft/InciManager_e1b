package asw.kafkaManager;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import asw.entities.Incidence;

@Configuration
@EnableKafka
public interface KafkaService {
	
	public void sendInci(Incidence incidence);
}
