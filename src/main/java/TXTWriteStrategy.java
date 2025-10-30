package src.main.java;

import java.io.FileWriter;
import java.io.PrintWriter;

public class TXTWriteStrategy implements WriteStrategy {
    @Override
    public void write(Game game, String filePath) throws Exception {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("MULTI-PLAYER JEOPARDY GAME REPORT\n");
            for (Player p : game.getPlayers()) {
                pw.println("Player: " + p.getName() + " | Score: " + p.getScore());
            }
        }
    }
}
