
import java.io.*;
import java.util.*;

public class CSVReader implements Reader {
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
