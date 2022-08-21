package my.playground.characters.controller;

import my.playground.characters.entity.Characters;
import my.playground.characters.entity.SuperHero;
import my.playground.characters.entity.SuperVillain;
import my.playground.characters.producer.CharactersProducer;
import my.playground.characters.repository.CharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.StreamSupport;

/**
 * REST service for superhero and super villain characters creation
 */
@RestController
public class CharactersController {

    @Autowired
    private CharactersRepository repository;

    @Autowired
    private CharactersProducer charactersProducer;

    @GetMapping("/characters")
    public List<Characters> all() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }

    @PostMapping("/addSuperHero")
    public Characters addSuperHero(@RequestBody SuperHero superHero) {
        SuperHero savedSuperHero = repository.save(superHero);
        charactersProducer.send(savedSuperHero);
        return savedSuperHero;
    }

    @PostMapping("/addSuperVillain")
    public Characters addSuperVillain(@RequestBody SuperVillain superVillain) {
        SuperVillain savedSuperVillain = repository.save(superVillain);
        charactersProducer.send(savedSuperVillain);
        return savedSuperVillain;
    }
}
