package my.playground.characters.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Super villain entity
 */
@Entity
@DiscriminatorValue(value = "SuperVillain")
public class SuperVillain extends Characters {

    public SuperVillain() {
    }

    public SuperVillain(String name, String power) {
        super(name, power);
    }
}
