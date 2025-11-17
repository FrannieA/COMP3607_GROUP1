
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.*;

/**
 * @author Tyler Baksh
 * An XML-based implementation of the {@link Reader} interface.
 * <p>
 * This class reads question data from an XML file, parses each entry, and
 * constructs a {@link QuestionBank} containing all loaded {@link Question}
 * objects.
 * </p>
 */
public class XMLReader implements Reader {

    /**
     * Reads an XML file containing question data and returns a populated
     * {@link QuestionBank}.
     * <p>
     * The XML file is expected to have the following structure:
     * <pre>
     * &lt;Questions&gt;
     *   &lt;QuestionItem&gt;
     *     &lt;Category&gt;Science&lt;/Category&gt;
     *     &lt;Value&gt;100&lt;/Value&gt;
     *     &lt;QuestionText&gt;What is H2O?&lt;/QuestionText&gt;
     *     &lt;Options&gt;
     *       &lt;OptionA&gt;Oxygen&lt;/OptionA&gt;
     *       &lt;OptionB&gt;Hydrogen&lt;/OptionB&gt;
     *       &lt;OptionC&gt;Water&lt;/OptionC&gt;
     *       &lt;OptionD&gt;Carbon Dioxide&lt;/OptionD&gt;
     *     &lt;/Options&gt;
     *     &lt;CorrectAnswer&gt;C&lt;/CorrectAnswer&gt;
     *   &lt;/QuestionItem&gt;
     *   ...
     * &lt;/Questions&gt;
     * </pre>
     * </p>
     *
     * @param filePath the path to the XML file to read
     * @return a {@link QuestionBank} filled with all questions from the file
     * @throws Exception if the file cannot be read or if parsing fails
     */
    @Override
    public QuestionBank read(String filePath) throws Exception {
        QuestionBank bank = new QuestionBank();
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode root = xmlMapper.readTree(new File(filePath));

        for (JsonNode item : root.withArray("QuestionItem")) {
            String category = item.path("Category").asText();
            int value = item.path("Value").asInt();
            String text = item.path("QuestionText").asText();

            JsonNode optionsNode = item.path("Options");
            String optionA = optionsNode.path("OptionA").asText();
            String optionB = optionsNode.path("OptionB").asText();
            String optionC = optionsNode.path("OptionC").asText();
            String optionD = optionsNode.path("OptionD").asText();

            String correctAnswer = item.path("CorrectAnswer").asText().trim();

            Question q = new Question(category, value, text, optionA, optionB, optionC, optionD,
                    correctAnswer.charAt(0));
            bank.addQuestion(q);
        }

        return bank;
    }
}