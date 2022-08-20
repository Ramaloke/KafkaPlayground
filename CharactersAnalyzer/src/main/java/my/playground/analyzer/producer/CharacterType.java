package my.playground.analyzer.producer;

/**
 * Characters types enum
 */
public enum CharacterType {
    SUPERHERO("SuperHero"),
    SUPERVILLAIN("SuperVillain");

    private String value;

    CharacterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
