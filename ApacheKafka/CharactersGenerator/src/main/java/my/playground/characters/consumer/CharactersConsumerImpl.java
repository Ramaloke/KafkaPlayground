package my.playground.characters.consumer;

import com.github.javafaker.Faker;
import my.playground.characters.entity.Characters;
import my.playground.characters.entity.SuperHero;
import my.playground.characters.entity.SuperVillain;
import my.playground.characters.service.CharacterCreationService;
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
 * Default implementation of {@link CharactersConsumer}
 */
public class CharactersConsumerImpl implements CharactersConsumer {

    private static final Logger log = LoggerFactory.getLogger(CharactersConsumerImpl.class);

    private static final String TOPIC = "analyzer";
    private static final String GROUP_ID_CONFIG_NAME = "charactersConsumer";

    private final KafkaConsumer<Long, String> consumer;

    @Autowired
    private Faker fakeData;

    @Autowired
    private CharacterCreationService characterCreationService;

    /**
     * Runs Kafka consumer in a separate thread
     */
    @PostConstruct
    public void startConsuming() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(() -> {
            log.info("Starting to consume events from topic {}", TOPIC);
            poll();
        });
    }

    public CharactersConsumerImpl(String bootstrapServers) {
        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(GROUP_ID_CONFIG, GROUP_ID_CONFIG_NAME);

        consumer = new KafkaConsumer<>(props);
    }

    /**
     * Fetch data from 'analyzer' Topic and creates random characters.
     */
    @Override
    public void poll() {
        consumer.subscribe(List.of(TOPIC));

        try {
            while(true) {
                ConsumerRecords<Long, String> events = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<Long, String> event : events) {
                    log.info("Event retrieved from topic {}, key = {}, value = {}",
                            TOPIC, event.key(), event.value());

                    createRandomCharacter(event.value());
                }
            }
        } finally {
            consumer.close();
        }
    }

    /**
     * Creates random character, based on character type.
     * @param characterType {@link Characters} class name
     */
    private void createRandomCharacter(String characterType) {
        if(characterType.equalsIgnoreCase(SuperHero.class.getSimpleName())) {
            characterCreationService.createSuperHero(fakeData.superhero().name(), fakeData.superhero().power());
        } else if(characterType.equalsIgnoreCase(SuperVillain.class.getSimpleName())) {
            characterCreationService.createSuperVillain(fakeData.superhero().name(), fakeData.superhero().power());
        } else {
            log.error("Unexpected Character type provided: {}", characterType);
        }
    }
}
