package src.main.java;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Logger implements EventListener {
    private final String logFile = "game_event_log.csv";

    public Logger() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(logFile))) {
            pw.println(
                    "Case_ID,Player_ID,Activity,Timestamp,Category,Question_Value,Answer_Given,Result,Score_After_Play");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String eventType, String details) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(logFile, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            pw.printf("%s,N/A,%s,%s,%s%n", UUID.randomUUID(), eventType, timestamp, details);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
