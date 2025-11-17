
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Game game = new XMLGameLoader().load("src/test/resource/sample_game_XML.xml");
        game.addPlayer(new Player("Alice"));
        game.addPlayer(new Player("Bob"));

        // Choose report type
        System.out.println("Choose report format (txt/pdf/docx): ");
        String type = sc.nextLine().trim().toLowerCase();

        WriteStrategy reportStrategy = WriteStrategyFactory.getStrategy(type);
        String reportFilename = "game_report." + type;

        EventManager manager = new EventManager();

        // Observer 1: Always produce CSV log
        manager.subscribe("gameEnded", new Logger());

        // Observer 2: Produce user-chosen report
        manager.subscribe("gameEnded", new ReportWriter(reportStrategy, reportFilename));

        game.setEventManager(manager);

        // Play the game
        game.play();
    }
}
