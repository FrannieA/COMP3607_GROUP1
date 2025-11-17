
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author Franchesca Ammon
 * A PDF-based implementation of the {@link WriteStrategy} interface.
 * <p>
 * This class writes the gameplay log of a {@link Game} instance and outputs each {@link Turn} to a PDF
 * file, including details such as player actions, timestamps, question
 * categories, answers given, and scores after each turn.
 * </p>
 */
public class PDFWriteStrategy implements WriteStrategy {

    /**
     * Writes gameplay data from the provided {@link Game} to a PDF file at the
     * specified path.
     * <p>
     * Each gameplay event (each {@link Turn}) is written as a formatted
     * section in the document.
     * </p>
     *
     * @param game     the game whose log data is to be exported
     * @param filePath the output PDF file path
     */
    @Override
    public void write(Game game, String filePath) {
        List<Turn> log = game.getGameplayLog();

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

            Paragraph title = new Paragraph("Game Log\n\n", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            int turnNumber = 1;
            for (Turn t : log) {
                document.add(new Paragraph("Turn: " + turnNumber++, normalFont));
                document.add(new Paragraph("Player: " + t.playerName, normalFont));
                document.add(new Paragraph("Category: " + t.category, normalFont));
                document.add(new Paragraph("Question: " + t.questionText, normalFont));
                document.add(new Paragraph("Answer Given: " + t.answerGiven, normalFont));
                document.add(new Paragraph("Result: " + (t.correct ? "Correct" : "Incorrect"), normalFont));
                document.add(new Paragraph("Points: " + t.points, normalFont));
                document.add(new Paragraph("Score After Turn: " + t.scoreAfterTurn + "\n", normalFont));
                document.add(new Paragraph("----------------------------------------\n"));
            }

            System.out.println("Game data successfully written to PDF file: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
