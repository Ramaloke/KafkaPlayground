package my.playground.characters.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Superhero entity
 */
@Entity
@DiscriminatorValue(value = "SuperHero")
public class SuperHero extends Characters {

    public SuperHero() {
    }

    public SuperHero(String name, String power) {
        super(name, power);
    }
}
