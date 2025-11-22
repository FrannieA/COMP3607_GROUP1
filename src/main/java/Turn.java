import java.time.LocalDateTime;

/**
 * @author Shania Siew
 *         Class representing a single turn in the game.
 */
public class Turn {
    public String playerName;
    public String category;
    public String questionText;
    public String answerGiven;
    public boolean correct;
    public int points;
    public int scoreAfterTurn;
    public LocalDateTime timestamp;

    /**
     * Constructs a Turn with the given parameters.
     * 
     * @param playerName     the name of the player
     * @param category       the category of the question
     * @param questionText   the text of the question
     * @param answerGiven    the answer given by the player
     * @param correct        whether the answer was correct
     * @param points         the points awarded for the turn
     * @param scoreAfterTurn the player's score after the turn
     */
    public Turn(String playerName, String category, String questionText, String answerGiven,
            boolean correct, int points, int scoreAfterTurn, LocalDateTime timestamp) {
        this.playerName = playerName;
        this.category = category;
        this.questionText = questionText;
        this.answerGiven = answerGiven;
        this.correct = correct;
        this.points = points;
        this.scoreAfterTurn = scoreAfterTurn;
        this.timestamp = timestamp;
    }
}
