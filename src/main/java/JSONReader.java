
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

/**
 * @author Tyler Baksh
 * A JSON-based implementation of the {@link Reader} interface.
 * <p>
 * This class reads question data from a JSON file, parses each entry, and
 * constructs a {@link QuestionBank} containing all loaded {@link Question}
 * objects.
 * </p>
 */
public class JSONReader implements Reader {
    
    /**
     * Reads a JSON file containing question data and returns a populated
     * {@link QuestionBank}.
     * <p>
     * The JSON file is expected to have an array of question objects with the
     * following structure:
     * <pre>
     * [
     *   {
     *     "Category": "Science",
     *     "Value": 100,
     *     "Question": "What is H2O?",
     *     "Options": {
     *       "A": "Oxygen",
     *       "B": "Hydrogen",
     *       "C": "Water",
     *       "D": "Carbon Dioxide"
     *     },
     *     "CorrectAnswer": "C"
     *   },
     *   ...
     * ]
     * </pre>
     * </p>
     *
     * @param filePath the path to the JSON file to read
     * @return a {@link QuestionBank} filled with all questions from the file
     * @throws Exception if the file cannot be read or if parsing fails
     */
    @Override
    public QuestionBank read(String filePath) throws Exception {
        QuestionBank bank = new QuestionBank();
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode root = mapper.readTree(new File(filePath));

            for (JsonNode node : root) {
                String category = node.get("Category").asText();
                int value = node.get("Value").asInt();
                String question = node.get("Question").asText();

                JsonNode optionsNode = node.get("Options");
                String[] options = { optionsNode.get("A").asText(), optionsNode.get("B").asText(),
                        optionsNode.get("C").asText(), optionsNode.get("D").asText() };

                String correctAnswer = node.get("CorrectAnswer").asText();

                Question q = new Question(category, value, question, options[0], options[1], options[2], options[3],
                        correctAnswer.charAt(0));

                bank.addQuestion(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bank;
    }
}