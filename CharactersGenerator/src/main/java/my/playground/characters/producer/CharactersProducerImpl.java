package my.playground.characters.producer;

import my.playground.characters.entity.Characters;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

/**
 * Default implementation of {@link CharactersProducer}
 */
public class CharactersProducerImpl implements CharactersProducer {
    private static final Logger log = LoggerFactory.getLogger(CharactersProducerImpl.class);
    private static final String TOPIC = "characters";
    private Producer<Long, String> producer;

    public CharactersProducerImpl(String bootstrapServers) {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        producer = new KafkaProducer<>(props);
    }

    @Override
    public void send(Characters character) {
        producer.send(new ProducerRecord<>(TOPIC, character.getId(), character.getClass().getSimpleName()),
                (event, exception) -> {
                    if (exception != null) {
                        log.error("Event wasn't sent", exception);
                    } else {
                        log.info("Produced event to topic {}, key = {}, value = {}", TOPIC, character.getId(), character);
                    }
                });
    }
}
