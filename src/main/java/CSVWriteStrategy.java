
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CSVWriteStrategy implements WriteStrategy {

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
                String timestamp = t.timestamp.format(formatter);
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