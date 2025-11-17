
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Franchesca Ammon
 * A TXT-based implementation of the {@link WriteStrategy} interface.
 * <p>
 * This class writes the gameplay log of a {@link Game} instance and outputs each {@link Turn} to a TXT
 * file in a human-readable format, including details such as player actions, question
 * categories, answers given, and scores after each turn.
 * </p>
 */
public class TXTWriteStrategy implements WriteStrategy {

    /**
     * Writes gameplay data from the provided {@link Game} to a TXT file at the
     * specified path.
     * <p>
     * The TXT file includes the following columns:
     * <pre>
     * Turn, Player, Category, Question, Answer Given, Result, Points, Score After Turn
     * </pre>
     * Each gameplay event (each {@link Turn}) is written as a single row.
     * </p>
     * 
     * @param game     the game whose log data is to be exported
     * @param filePath the output TXT file path
     */
    @Override
    public void write(Game game, String filePath) {
        List<Turn> log = game.getGameplayLog();

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("=== Game Log ===\n");

            int turnNumber = 1;
            for (Turn t : log) {
                pw.println("Turn: " + turnNumber++);
                pw.println("Player: " + t.playerName);
                pw.println("Category: " + t.category);
                pw.println("Question: " + t.questionText);
                pw.println("Answer Given: " + t.answerGiven);
                pw.println("Result: " + (t.correct ? "Correct" : "Incorrect"));
                pw.println("Points: " + t.points);
                pw.println("Score After Turn: " + t.scoreAfterTurn);
                pw.println("----------------------------------------");
            }

            System.out.println("Game data successfully written to TXT file: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}