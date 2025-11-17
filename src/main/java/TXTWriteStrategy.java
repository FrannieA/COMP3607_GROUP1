
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class TXTWriteStrategy implements WriteStrategy {

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