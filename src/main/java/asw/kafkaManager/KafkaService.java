package asw.kafkaManager;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import asw.Incidence;

@Configuration
@EnableKafka
public interface KafkaService {
	
	public void sendInci(Incidence incidence);
}
