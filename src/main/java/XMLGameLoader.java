
import java.util.ArrayList;

public class XMLGameLoader implements GameLoader {
    @Override
    public Game load(String filePath) throws Exception {
        XMLReader reader = new XMLReader();
        QuestionBank bank = reader.read(filePath);
        return new Game(new ArrayList<>(), bank);
    }
}
