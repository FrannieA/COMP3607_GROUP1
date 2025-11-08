package src.main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Logger implements EventListener {
    private WriteStrategy writeStrategy;

    public Logger(WriteStrategy writeStrategy) {
        this.writeStrategy = writeStrategy;
    }

    @Override
    public void update(String eventType, Object details) {
        if ("turnStarted".equals(eventType) && details instanceof Map) {
            var map = (Map<?, ?>) details;
            System.out.println("Event: turnStarted | Player: " + map.get("player"));
        } else if ("questionAnswered".equals(eventType) && details instanceof Turn) {
            Turn t = (Turn) details;
            String ts = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            System.out.printf("Event Logged: Player=%s, Question=%s, Answer=%s, Correct=%b, Score=%d (at %s)%n",
                    t.playerName, t.questionText, t.answerGiven, t.correct, t.scoreAfterTurn, ts);
        } else if ("gameEnded".equals(eventType) && details instanceof Game) {
            Game g = (Game) details;
            System.out.println("Event: gameEnded — writing final report using strategy.");
            // choose a file name
            // instead
            writeStrategy.write(g, "game_report.csv");
        }
    }
}
