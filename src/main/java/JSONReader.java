
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;

public class JSONReader implements Reader {
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