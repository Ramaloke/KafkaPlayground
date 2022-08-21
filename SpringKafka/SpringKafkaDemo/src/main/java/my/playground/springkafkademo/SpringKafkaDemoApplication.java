package my.playground.springkafkademo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SpringKafkaDemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringKafkaDemoApplication.class).run(args);
    }

}
