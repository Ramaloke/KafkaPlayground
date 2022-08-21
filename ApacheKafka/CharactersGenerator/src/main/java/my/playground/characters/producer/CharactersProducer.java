package my.playground.characters.producer;

import my.playground.characters.entity.Characters;

/**
 * Kafka {@link Characters} producer
 */
public interface CharactersProducer {
    /**
     * Produces event to a specific Kafka Topic
     * @param character {@link Characters} object
     */
    void send(Characters character);
}
