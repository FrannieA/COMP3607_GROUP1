
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Shania Siew
 * A CSV-based implementation of the {@link WriteStrategy} interface.
 * <p>
 * This class writes the gameplay log of a {@link Game} instance and outputs each {@link Turn} to a CSV
 * file, including details such as player actions, timestamps, question
 * categories, answers given, and scores after each turn.
 * </p>
 */
public class CSVWriteStrategy implements WriteStrategy {
    /**
     * Writes gameplay data from the provided {@link Game} to a CSV file at the
     * specified path.
     * <p>
     * The CSV file includes the following columns:
     * <pre>
     * Case_ID, Player_ID, Activity, Timestamp, Category,
     * Question_Value, Answer_Given, Result, Score_After_Play
     * </pre>
     * Each gameplay event (each {@link Turn}) is written as a single row.
     * </p>
     *
     * @param game     the game whose log data is to be exported
     * @param filePath the output CSV file path
     */
    @Override
    public void write(Game game, String filePath) {
        List<Turn> log = game.getGameplayLog();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            // Correct header
            pw.println(
                    "Case_ID,Player_ID,Activity,Timestamp,Category,Question_Value,Answer_Given,Result,Score_After_Play");

            int caseId = 1;
            for (Turn t : log) {
                String timestamp = LocalDateTime.now().format(formatter);
                String activity = "Answered Question";
                String result = t.correct ? "Correct" : "Incorrect";

                pw.printf("%d,%s,%s,%s,%s,%d,%s,%s,%d%n",
                        caseId++,
                        t.playerName,
                        activity,
                        timestamp,
                        t.category,
                        t.points,
                        t.answerGiven,
                        result,
                        t.scoreAfterTurn);
            }

            System.out.println("Game data successfully written to CSV file: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}