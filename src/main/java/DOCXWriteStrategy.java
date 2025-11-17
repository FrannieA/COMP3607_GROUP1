import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Franchesca Ammon
 * A DOCX-based implementation of the {@link WriteStrategy} interface.
 * <p>
 * This class writes the gameplay log of a {@link Game} instance and outputs each {@link Turn} to a DOCX
 * file, including details such as player actions, timestamps, question
 * categories, answers given, and scores after each turn.
 * </p>
 */
public class DOCXWriteStrategy implements WriteStrategy {

    /**
     * Writes gameplay data from the provided {@link Game} to a DOCX file at the
     * specified path.
     * <p>
     * Each gameplay event (each {@link Turn}) is written as a formatted
     * section in the document.
     * </p>
     *
     * @param game     the game whose log data is to be exported
     * @param filePath the output DOCX file path
     */
    @Override
    public void write(Game game, String filePath) {
        List<Turn> log = game.getGameplayLog();

        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph title = document.createParagraph();
            title.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun titleRun = title.createRun();
            titleRun.setText("Game Log");
            titleRun.setBold(true);
            titleRun.setFontSize(18);

            for (int i = 0; i < log.size(); i++) {
                Turn t = log.get(i);

                XWPFParagraph p = document.createParagraph();
                XWPFRun run = p.createRun();
                run.setText("Turn: " + (i + 1));
                run.addBreak();
                run.setText("Player: " + t.playerName);
                run.addBreak();
                run.setText("Category: " + t.category);
                run.addBreak();
                run.setText("Question: " + t.questionText);
                run.addBreak();
                run.setText("Answer Given: " + t.answerGiven);
                run.addBreak();
                run.setText("Result: " + (t.correct ? "Correct" : "Incorrect"));
                run.addBreak();
                run.setText("Points: " + t.points);
                run.addBreak();
                run.setText("Score After Turn: " + t.scoreAfterTurn);
                run.addBreak();
                run.addBreak();
            }

            try (FileOutputStream out = new FileOutputStream(filePath)) {
                document.write(out);
            }

            System.out.println("Game data successfully written to DOCX file: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
