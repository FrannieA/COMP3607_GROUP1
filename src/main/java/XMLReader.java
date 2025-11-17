
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.*;

public class XMLReader implements Reader {
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