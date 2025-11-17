
/**
 * @author Shania Siew
 * Class representing a player in the game.
 */

public class Player {
    private String name;
    private int score;

    /**
     * Constructs a Player with the given name.
     * player's initial score is set to 0.
     * @param name
     */
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Retrieves the player's name.
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the player's current score.
     * @return the player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds points to the player's score.
     * @param points the number of points to add
     */
    public void addScore(int points) {
        this.score += points;
    }

    // public void subtractScore(int points) {
    // this.score -= points;
    // }
}
