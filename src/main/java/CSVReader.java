import java.io.*;
import java.util.*;

/**
 * @author Shania Siew
 * A CSV-based implementation of the {@link Reader} interface.
 * <p>
 * This class reads question data from a CSV file, parses each line, and
 * constructs a {@link QuestionBank} containing all loaded {@link Question}
 * objects. It supports CSV fields that may contain commas inside quotes.
 * </p>
 */
public class CSVReader implements Reader {

    /**
     * Reads a CSV file containing question data and returns a populated
     * {@link QuestionBank}.
     * <p>
     * The CSV file is expected to have the following structure:
     * <pre>
     * Category, Value, QuestionText, OptionA, OptionB, OptionC, OptionD, CorrectAnswer
     * </pre>
     * The first line (header) is automatically skipped.
     * </p>
     *
     * @param filePath the path to the CSV file to read
     * @return a {@link QuestionBank} filled with all questions from the file
     * @throws Exception if the file cannot be read or if parsing fails
     */
    @Override
    public QuestionBank read(String filePath) throws Exception {
        QuestionBank bank = new QuestionBank();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String category = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());
                String text = parts[2].trim();
                String[] options = Arrays.copyOfRange(parts, 3, 7);
                String correct = parts[7].trim();

                bank.addQuestion(new Question(category, value, text,
                        options[0], options[1], options[2], options[3], correct.charAt(0)));
            }
        }

        return bank;
    }
}
