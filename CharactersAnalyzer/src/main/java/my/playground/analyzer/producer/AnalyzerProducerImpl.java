package my.playground.analyzer.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

/**
 * Default implementation of {@link AnalyzerProducer}
 */
public class AnalyzerProducerImpl implements AnalyzerProducer {

    private static final Logger log = LoggerFactory.getLogger(AnalyzerProducerImpl.class);
    private static final String TOPIC = "analyzer";
    private KafkaProducer<Long, String> producer;
    private AtomicLong kafkaKey = new AtomicLong(0L);

    public AnalyzerProducerImpl(String bootstrapServers) {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        producer = new KafkaProducer<>(props);
    }

    @Override
    public void send(CharacterType type) {
        long key = kafkaKey.getAndIncrement();
        producer.send(new ProducerRecord<>(TOPIC, key, type.getValue()),
                (event, exception) -> {
                    if (exception != null) {
                        log.error("Event wasn't sent", exception);
                    } else {
                        log.info("Produced event to topic {}, key = {}, value = {}", TOPIC, key, type.getValue());
                    }
                });
    }
}
