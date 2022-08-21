package my.playground.springkafkademo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SpringKafkaConsumer {

    private static Logger log = LoggerFactory.getLogger(SpringKafkaConsumer.class);

    @KafkaListener(topics = "${topic.name.consumer}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(ConsumerRecord<String, String> payload) {
        log.info("Consume event from topic: {}", payload.topic());
        log.info("Key: {}", payload.key());
        log.info("Value: {}", payload.value());
        log.info("Timestamp: {}", payload.timestamp());
        log.info("Headers: {}", payload.headers());
    }
}
