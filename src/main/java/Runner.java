package src.main.java;

import java.util.*;

public class Runner {
    public static void main(String[] args) throws Exception {
        List<Player> players = Arrays.asList(new Player("Alice"), new Player("Bob"));
        CSVGameLoader loader = new CSVGameLoader();
        Game game = loader.load("sample_game_CSV.csv"); // change path
        game.getPlayers().addAll(players); // add players
        game.play();
        WriteStrategy writer = WriteStrategyFactory.getStrategy("csv");
        writer.write(game, "game_results.csv");
    }
}