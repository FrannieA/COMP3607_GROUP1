/**
 * @author Shania Siew
 * Class responsible for writing game reports using different strategies.
 * Implements the EventListener interface to respond to game events.
 */
public class ReportWriter implements EventListener {
    private WriteStrategy strategy;
    private String outputFile;

    /**
     * Constructor for ReportWriter.
     * @param strategy the strategy to use for writing the report
     * @param outputFile the output file path
     */
    public ReportWriter(WriteStrategy strategy, String outputFile) {
        this.strategy = strategy;
        this.outputFile = outputFile;
    }

    /**
     * Handles game event updates and writes game reports when the game ends.
     * <p>
     * If the event type is "gameEnded" and the data is an instance of Game,
     * the strategy is used to write the game report to the output file.
     * </p>
     * @param eventType the type of event
     * @param data the data associated with the event
   */     
  @Override
    public void update(String eventType, Object data) {
        if (eventType.equals("gameEnded") && data instanceof Game) {
            strategy.write((Game) data, outputFile);
        }
    }
}
