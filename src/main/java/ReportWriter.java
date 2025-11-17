
public class ReportWriter implements EventListener {
    private WriteStrategy strategy;
    private String outputFile;

    public ReportWriter(WriteStrategy strategy, String outputFile) {
        this.strategy = strategy;
        this.outputFile = outputFile;
    }

    @Override
    public void update(String eventType, Object data) {
        if (eventType.equals("gameEnded") && data instanceof Game) {
            strategy.write((Game) data, outputFile);
        }
    }
}
