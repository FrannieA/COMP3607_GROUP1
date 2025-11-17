
import java.util.ArrayList;

public class JSONGameLoader implements GameLoader {
    @Override
    public Game load(String filePath) throws Exception {
        JSONReader reader = new JSONReader();
        QuestionBank bank = reader.read(filePath);
        return new Game(new ArrayList<>(), bank);
    }
}
