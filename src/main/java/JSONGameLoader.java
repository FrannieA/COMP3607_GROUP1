
import java.util.ArrayList;

/**
 * @author Shania Siew
 * Loads a {@link Game} instance from a JSON file.
 * <p>
 * This class uses a {@link JSONReader} to read question data from the
 * specified JSON file and constructs a new {@link Game} object using the
 * resulting {@link QuestionBank}.
 * </p>
 */
public class JSONGameLoader implements GameLoader {
   
    /**
     * Loads a game using the data found in the provided JSON file path.
     *
     * @param filePath the path to the JSON file containing question data
     * @return a fully constructed {@link Game} populated with questions
     * @throws Exception if an error occurs while reading the JSON file
     */
    @Override
    public Game load(String filePath) throws Exception {
        JSONReader reader = new JSONReader();
        QuestionBank bank = reader.read(filePath);
        return new Game(new ArrayList<>(), bank);
    }
}
