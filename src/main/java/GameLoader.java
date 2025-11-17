/**
 * @author Shania Siew
 * Interface for loading a Game from a file.
 */
public interface GameLoader {
    Game load(String filePath) throws Exception;

}
