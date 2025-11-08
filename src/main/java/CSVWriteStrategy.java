package src.main.java;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class CSVWriteStrategy implements WriteStrategy {

    @Override
    public void write(Game game, String filePath) {
        List<Turn> log = game.getGameplayLog();

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            // Header
            pw.println("Turn,Player,Category,Question,AnswerGiven,Correct,Points,ScoreAfterTurn");

            // Write each turn
            int turnNumber = 1;
            for (Turn t : log) {
                pw.printf("%d,%s,%s,%s,%s,%s,%d,%d%n",
                        turnNumber++,
                        t.playerName,
                        t.category,
                        t.questionText.replace(",", " "),
                        t.answerGiven,
                        t.correct ? "Correct" : "Incorrect",
                        t.points,
                        t.scoreAfterTurn);
            }

            System.out.println("Game data successfully written to CSV file: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
