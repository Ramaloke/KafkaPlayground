package my.playground.characters.config;

import com.github.javafaker.Faker;
import my.playground.characters.consumer.CharactersConsumer;
import my.playground.characters.consumer.CharactersConsumerImpl;
import my.playground.characters.entity.SuperHero;
import my.playground.characters.entity.SuperVillain;
import my.playground.characters.producer.CharactersProducerImpl;
import my.playground.characters.repository.CharactersRepository;
import my.playground.characters.service.CharacterCreationService;
import my.playground.characters.service.impl.CharacterCreationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfig {

    private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    Environment env;

    @Bean
    CommandLineRunner initDatabase(CharactersRepository repository) {
        return args -> {
            Faker fakeData = new Faker();
            log.info("Preloading: {}", repository.save(new SuperHero(fakeData.superhero().name(), fakeData.superhero().power())));
            log.info("Preloading: {}", repository.save(new SuperVillain(fakeData.superhero().name(), fakeData.superhero().power())));
        };
    }

    @Bean
    public CharactersProducerImpl characterProducer() {
        return new CharactersProducerImpl(env.getProperty("bootstrap.servers"));
    }

    @Bean
    public CharactersConsumer charactersConsumer() {
        return new CharactersConsumerImpl(env.getProperty("bootstrap.servers"));
    }

    @Bean
    public CharacterCreationService characterCreationService() {
        return new CharacterCreationServiceImpl();
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
