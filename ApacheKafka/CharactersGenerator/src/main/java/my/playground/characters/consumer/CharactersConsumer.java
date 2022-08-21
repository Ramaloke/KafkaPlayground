package my.playground.characters.consumer;

import my.playground.characters.entity.Characters;

/**
 * Kafka {@link Characters} consumer
 */
public interface CharactersConsumer {
    /**
     * Subscribes to a specific Kafka Topic and fetches its data
     */
    void poll();
}
