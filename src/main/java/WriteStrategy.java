/**
 * @author Shania Siew
 * Interface defining a strategy for writing game data to a file.
*/
public interface WriteStrategy {
    public void write(Game game, String filePath);

}
