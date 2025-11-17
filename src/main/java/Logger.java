/**
 * @author Shania Siew
 * Logger class that implements EventListener to log game events to a CSV file.
 */
public class Logger implements EventListener {
    private CSVWriteStrategy csvWriter;

    /**
     * Constructs a Logger with a CSVWriteStrategy for logging.
     */
    public Logger() {
        this.csvWriter = new CSVWriteStrategy();
    }

    /** 
     * Handles event updates and logs game end events to a CSV file.
     * @param eventType the type of event
     * @param data the data associated with the event
     */
    @Override
    public void update(String eventType, Object data) {
        if (eventType.equals("gameEnded") && data instanceof Game) {
            csvWriter.write((Game) data, "game_event_log.csv");
        }
    }
}
