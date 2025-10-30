package src.main.java;

import java.util.*;

public class Game {
    private List<Player> players = new ArrayList<>();
    private QuestionBank questionBank;

    public Game() {
        this.players = new ArrayList<>();
        this.questionBank = new QuestionBank();
    }

    public Game(List<Player> players, QuestionBank questionBank) {
        this.players = players;
        this.questionBank = questionBank;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public QuestionBank getQuestionBank() {
        return questionBank;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        int turn = 0;
        System.out.println("Welcome to the Quiz Game!");

        while (true) {
            Player player = players.get(turn % players.size());
            System.out.println("\nIt's " + player.getName() + "'s turn!");

            List<String> categories = questionBank.getCategories();
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i));
            }

            System.out.print("Choose a category: ");
            int catChoice = sc.nextInt();
            sc.nextLine();
            String category = categories.get(catChoice - 1);

            List<Question> available = new ArrayList<>();
            for (Question q : questionBank.getQuestionsByCategory(category)) {
                if (!q.isAnswered())
                    available.add(q);
            }
            if (available.isEmpty()) {
                System.out.println("No remaining questions in this category.");
                continue;
            }

            System.out.println("Available questions:");
            for (int i = 0; i < available.size(); i++)
                System.out.println((i + 1) + ". " + available.get(i).getValue() + " pts");

            System.out.print("Choose question: ");
            int qChoice = sc.nextInt();
            sc.nextLine();
            Question q = available.get(qChoice - 1);

            System.out.println("\n" + q);
            System.out.print("Your answer (A/B/C/D): ");
            String ans = sc.nextLine();

            if (q.checkAnswer(ans)) {
                System.out.println("✅ Correct!");
                player.addScore(q.getValue());
            } else {
                System.out.println("❌ Incorrect! Correct answer: " + q.getCorrectAnswer());
                player.subtractScore(q.getValue());
            }
            q.setAnswered(true);
            System.out.println(player.getName() + " score: " + player.getScore());
            turn++;
        }
    }
}
