package my.playground.characters.service;

/**
 * Service for different types of {@link my.playground.characters.entity.Characters} creation
 */
public interface CharacterCreationService {

    /**
     * Creates superhero character
     * @param name superhero name
     * @param power superhero power
     */
    void createSuperHero(String name, String power);

    /**
     * Creates super villain character
     * @param name super villain name
     * @param power super villain power
     */
    void createSuperVillain(String name, String power);
}
