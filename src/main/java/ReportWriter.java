package src.main.java;

// ReportWriter.java
public class ReportWriter implements EventListener {
    @Override
    public void update(String eventType, String details) {
        if (eventType.equals("GenerateReport")) {
            System.out.println("Generating report: " + details);
        }
    }
}
