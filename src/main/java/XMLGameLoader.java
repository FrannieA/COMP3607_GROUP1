
import java.util.ArrayList;
/**
 * @author Shania Siew
 * Loads a {@link Game} instance from an XML file.
 * <p>
 * This class uses an {@link XMLReader} to read question data from the
 * specified XML file and constructs a new {@link Game} object using the
 * resulting {@link QuestionBank}.
 * </p>
 */
public class XMLGameLoader implements GameLoader {

/**
 * Loads a game using the data found in the provided XML file path.
 * 
 * @param filePath the path to the XML file containing question data
 * @return a fully constructed {@link Game} populated with questions
 * @throws Exception if an error occurs while reading the XML file.
 */
    @Override
    public Game load(String filePath) throws Exception {
        XMLReader reader = new XMLReader();
        QuestionBank bank = reader.read(filePath);
        return new Game(new ArrayList<>(), bank);
    }
}
