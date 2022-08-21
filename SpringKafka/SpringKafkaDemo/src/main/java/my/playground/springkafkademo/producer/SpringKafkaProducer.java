package my.playground.springkafkademo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(SpringKafkaProducer.class);

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public SpringKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message) {
        log.info("Payload sent: {}", message);
        kafkaTemplate.send(topicName, message);
    }
}
