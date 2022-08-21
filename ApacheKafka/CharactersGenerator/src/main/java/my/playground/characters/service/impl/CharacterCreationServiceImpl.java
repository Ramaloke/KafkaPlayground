package my.playground.characters.service.impl;

import my.playground.characters.entity.Characters;
import my.playground.characters.entity.SuperHero;
import my.playground.characters.entity.SuperVillain;
import my.playground.characters.repository.CharactersRepository;
import my.playground.characters.service.CharacterCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that creates superheroes and super villains.
 */
@Service
public class CharacterCreationServiceImpl implements CharacterCreationService {

    private static final Logger log = LoggerFactory.getLogger(CharacterCreationServiceImpl.class);

    @Autowired
    private CharactersRepository repository;

    @Override
    public void createSuperHero(String name, String power) {
        Characters superHero = repository.save(new SuperHero(name, power));
        log.info("SuperHero entity created: {}", superHero);
    }

    @Override
    public void createSuperVillain(String name, String power) {
        Characters superVillain = repository.save(new SuperVillain(name, power));
        log.info("SuperVillain entity created: {}", superVillain);
    }
}
