package my.playground.analyzer.consumer;

/**
 * Common Kafka consumer
 */
public interface AnalyzerConsumer {
    /**
     * Subscribes to a specific Kafka Topic and fetches its data
     */
    void poll();
}
