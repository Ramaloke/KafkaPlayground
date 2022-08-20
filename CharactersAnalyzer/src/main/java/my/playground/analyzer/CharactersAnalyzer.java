package my.playground.analyzer;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CharactersAnalyzer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CharactersAnalyzer.class).web(WebApplicationType.NONE).run(args);
    }
}
