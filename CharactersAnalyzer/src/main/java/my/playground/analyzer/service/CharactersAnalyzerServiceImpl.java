package my.playground.analyzer.service;

import my.playground.analyzer.producer.AnalyzerProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

import static my.playground.analyzer.producer.CharacterType.SUPERHERO;
import static my.playground.analyzer.producer.CharacterType.SUPERVILLAIN;

/**
 * Service that analyzes types of created super characters and tries to balance character types numbers
 */
@Service
public class CharactersAnalyzerServiceImpl implements CharactersAnalyzerService {

    private static final Logger log = LoggerFactory.getLogger(CharactersAnalyzerServiceImpl.class);

    private final AtomicInteger superHeroesCounter = new AtomicInteger(0);
    private final AtomicInteger superVillainsCounter = new AtomicInteger(0);

    @Autowired
    private AnalyzerProducer analyzerProducer;

    @Override
    public void analyzeCharactersData(String value) {
        if (SUPERHERO.getValue().equalsIgnoreCase(value)) {
            tryToCreateRandomSuperVillain(superHeroesCounter.incrementAndGet());
        } else if (SUPERVILLAIN.getValue().equalsIgnoreCase(value)) {
            tryToCreateRandomSuperHero(superVillainsCounter.incrementAndGet());
        } else {
            log.error("Unexpected CharacterType value provided: {}", value);
        }
    }

    /**
     * Produces kafka event to create random SuperVillain for every 5 SuperHeroes created.
     * @param counter manually created SuperHeroes amount
     */
    private void tryToCreateRandomSuperVillain(int counter) {
        if(counter % 5 == 0) {
            analyzerProducer.send(SUPERVILLAIN);
        }
    }

    /**
     * Produces kafka event to create random SuperHero for every 3 SuperVillains created.
     * @param counter manually created SuperVillains amount
     */
    private void tryToCreateRandomSuperHero(int counter) {
        if(counter % 3 == 0) {
            analyzerProducer.send(SUPERHERO);
        }
    }
}
