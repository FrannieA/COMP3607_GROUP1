
public class Turn {
    public String playerName;
    public String category;
    public String questionText;
    public String answerGiven;
    public boolean correct;
    public int points;
    public int scoreAfterTurn;

    public Turn(String playerName, String category, String questionText, String answerGiven,
            boolean correct, int points, int scoreAfterTurn) {
        this.playerName = playerName;
        this.category = category;
        this.questionText = questionText;
        this.answerGiven = answerGiven;
        this.correct = correct;
        this.points = points;
        this.scoreAfterTurn = scoreAfterTurn;
    }
}
