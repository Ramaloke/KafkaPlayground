package my.playground.characters.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Abstract entity for different types of characters like superheroes, super villains etc.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Characters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String power;

    protected Characters() {
    }

    protected Characters(String name, String power) {
        this.name = name;
        this.power = power;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characters character = (Characters) o;
        return Objects.equals(id, character.id) && Objects.equals(name, character.name) && Objects.equals(power, character.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, power);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", power='" + power + '\'' +
                '}';
    }
}
