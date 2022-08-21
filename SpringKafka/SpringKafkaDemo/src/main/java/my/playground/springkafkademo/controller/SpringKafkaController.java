package my.playground.springkafkademo.controller;

import my.playground.springkafkademo.producer.SpringKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/spring_kafka")
public class SpringKafkaController {

    @Autowired
    private SpringKafkaProducer producer;

    @PostMapping("/sendMessage")
    public ResponseEntity<String> send(@RequestBody String message) {
        producer.send(message);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
