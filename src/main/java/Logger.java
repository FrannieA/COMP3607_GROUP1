public class Logger implements EventListener {
    private CSVWriteStrategy csvWriter;

    public Logger() {
        this.csvWriter = new CSVWriteStrategy();
    }

    @Override
    public void update(String eventType, Object data) {
        if (eventType.equals("gameEnded") && data instanceof Game) {
            csvWriter.write((Game) data, "game_event_log.csv");
        }
    }
}
