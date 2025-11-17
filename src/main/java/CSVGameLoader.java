
import java.util.ArrayList;

/**
 * @author Shania Siew
 * Loads a {@link Game} instance from a CSV file.
 * <p>
 * This class uses a {@link CSVReader} to read question data from the
 * specified CSV file and constructs a new {@link Game} object using the
 * resulting {@link QuestionBank}.
 * </p>
 */
public class CSVGameLoader implements GameLoader {
    
    /**
     * Loads a game using the data found in the provided CSV file path.
     *
     * @param filePath the path to the CSV file containing question data
     * @return a fully constructed {@link Game} populated with questions
     * @throws Exception if an error occurs while reading the CSV file
     */
    @Override
    public Game load(String filePath) throws Exception {
        CSVReader reader = new CSVReader();
        QuestionBank bank = reader.read(filePath);
        return new Game(new ArrayList<>(), bank);
    }
}
