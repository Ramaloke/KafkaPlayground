package my.playground.analyzer.producer;

/**
 * Kafka producer for Characters Analyzer results.
 */
public interface AnalyzerProducer {
    /**
     * Produces event to a specific Kafka Topic
     * @param type characters type represented by {@link CharacterType}
     */
    void send(CharacterType type);
}
