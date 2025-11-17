
import java.util.ArrayList;

public class CSVGameLoader implements GameLoader {
    @Override
    public Game load(String filePath) throws Exception {
        CSVReader reader = new CSVReader();
        QuestionBank bank = reader.read(filePath);
        return new Game(new ArrayList<>(), bank);
    }
}
