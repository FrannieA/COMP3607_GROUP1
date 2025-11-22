
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Game game = new XMLGameLoader().load("src/test/resource/sample_game_XML.xml");

        // -----------------------------
        // CHOOSE REPORT FORMAT FIRST
        // -----------------------------
        System.out.println("Choose report format (txt/pdf/docx): ");
        String type = sc.nextLine().trim().toLowerCase();

        WriteStrategy reportStrategy = WriteStrategyFactory.getStrategy(type);
        String reportFilename = "game_report." + type;

        // -----------------------------
        // ADD PLAYERS INTERACTIVELY
        // -----------------------------
        System.out.println("Enter players (1-4 players).");
        System.out.println("Type 'start' to begin the game.");

        while (true) {
            System.out.print("Enter player name or 'start': ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("start")) {
                break;
            }

            if (game.getPlayers().size() >= 4) {
                System.out.println("Maximum of 4 players reached.");
                continue;
            }

            if (input.isEmpty()) {
                System.out.println("Player name cannot be empty.");
                continue;
            }

            game.addPlayer(new Player(input));
            System.out.println("Added player: " + input);
        }

        // -----------------------------
        // EVENT MANAGER SETUP
        // -----------------------------
        EventManager manager = new EventManager();

        // Always produce CSV log
        manager.subscribe("gameEnded", new Logger());

        // Produce selected report
        manager.subscribe("gameEnded", new ReportWriter(reportStrategy, reportFilename));

        game.setEventManager(manager);

        // -----------------------------
        // PLAY THE GAME
        // -----------------------------
        System.out.println("Starting game...");
        game.play();

        sc.close();
    }
}
