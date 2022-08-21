package my.playground.analyzer.consumer;

import my.playground.analyzer.service.CharactersAnalyzerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

/**
 * Default implementation of {@link AnalyzerConsumer}
 */
public class AnalyzerConsumerImpl implements AnalyzerConsumer {

    private static final Logger log = LoggerFactory.getLogger(AnalyzerConsumerImpl.class);
    private static final String TOPIC = "characters";
    private static final String GROUP_ID_CONFIG_NAME = "charactersAnalyzerConsumer";
    private final KafkaConsumer<Long, String> consumer;

    @Autowired
    private CharactersAnalyzerService charactersAnalyzer;

    @PostConstruct
    public void startConsuming() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            log.info("Starting to consume events from topic {}", TOPIC);
            poll();
        });
    }

    public AnalyzerConsumerImpl(String bootstrapServers) {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(GROUP_ID_CONFIG, GROUP_ID_CONFIG_NAME);

        consumer = new KafkaConsumer<>(props);
    }

    @Override
    public void poll() {
        consumer.subscribe(List.of(TOPIC));
        try {
            while(true) {
                ConsumerRecords<Long, String> events = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<Long, String> event : events) {
                    log.info("Event retrieved from topic {}, key = {}, value = {}",
                            event.topic(), event.key(), event.value());

                    charactersAnalyzer.analyzeCharactersData(event.value());
                }
            }
        } finally {
            consumer.close();
        }
    }
}
