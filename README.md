Playing around with Kafka.

Spring Initializer settings:
- H2
- JPA
- WEB

To run as executable via maven from the specific module:
    
    mvn clean spring-boot:run

Key objects:
- KafkaPlayground.postman_collection.json - Postman collection with REST requests
- docker/kafka_playground/docker-compose.yml - Use it to run Kafka:
  - zookeeper - Kafka configuration storage
  - broker - Kafka itself
  - kafdrop - Web UI for displaying Kafka Broker content 

**ApacheKafka/CharactersGenerator module**
- config.AppConfig.java - Spring configuration. DB initialization with data is located there.


- controller.CharactersController.java - Rest controller with REST endpoints.
 

- entity.Characters.java - Abstract entity for SuperHero and SuperVillains entities. SINGLE_TABLE inheritance type is used.
- entity.SuperHero.java - Entity that represents SuperHero entry.
- entity.SuperVillain.java - Entity that represents SuperVillain entry.


- repository.CharactersRepository.java - Repository with CRUD operations for Character entities (SuperHero, SuperVillain)


- consumer.CharactersConsumerImpl.java - Common Apache Kafka consumer that fetches and processes data from 'analyzer' Kafka Topic
- producer.CharactersProducerImpl.java - Common Apache Kafka producer that sends events to 'characters' Kafka Topic

**ApacheKafka/CharactersAnalyzer module**
- config.AppConfig.java - Spring configuration

- consumer.AnalyzerConsumerImpl.java - Common Apache Kafka consumer that fetches data from 'characters' Kafka Topic and sends data to CharactersAnalyzer service.
- producer.AnalyzerProducerImpl.java - Common Apache Kafka producer that sends events to 'analyzer' Kafka Topic
- service.CharactersAnalyzerServiceImpl.java - Service that analyzes data retrieved from AnalyzerConsumer

**SpringKafka/SpringKafkaDemo module**
- resources/application.yaml - Spring Kafka properties
- consumer.SpringKafkaConsumer.java - Spring Kafka consumer example. 
- producer.SpringKafkaProducer.java - Spring Kafka producer example.
- controller.SpringKafkaController.java - Simple REST Controller with endpoint that sends a message which in its turn is produced to the Kafka Topic by SpringKafkaProducer and then consumed by SpringKafkaConsumer