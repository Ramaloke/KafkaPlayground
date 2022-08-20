package my.playground.analyzer.config;

import my.playground.analyzer.consumer.AnalyzerConsumer;
import my.playground.analyzer.consumer.AnalyzerConsumerImpl;
import my.playground.analyzer.producer.AnalyzerProducer;
import my.playground.analyzer.producer.AnalyzerProducerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    public AnalyzerConsumer charactersConsumer() {
        return new AnalyzerConsumerImpl(env.getProperty("bootstrap.servers"));
    }

    @Bean
    public AnalyzerProducer analyzerProducer() {
        return new AnalyzerProducerImpl(env.getProperty("bootstrap.servers"));
    }
}
