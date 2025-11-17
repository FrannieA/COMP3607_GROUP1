import java.util.*;

public class Game {
    private List<Player> players = new ArrayList<>();
    private QuestionBank questionBank;
    private List<Turn> gameplayLog = new ArrayList<>();
    private EventManager eventManager; // observer pattern

    public List<Turn> getGameplayLog() {
        return gameplayLog;
    }

    public Game() {
        this.players = new ArrayList<>();
        this.questionBank = new QuestionBank();
    }

    public Game(List<Player> players, QuestionBank questionBank) {
        this.players = players;
        this.questionBank = questionBank;
    }

    public void setEventManager(EventManager eventManager) {
        this.eventManager = eventManager;
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

    private void notifyEvent(String eventType, Object data) {
        if (eventManager != null) {
            eventManager.notify(eventType, data);
        }
    }

    public void play() {
        if (players.size() < 1 || players.size() > 4) {
            throw new IllegalStateException("Game only supports 1 and 4 players.");
        }
        Scanner sc = new Scanner(System.in);
        int turn = 0;
        System.out.println("Welcome to the Quiz Game!");
        System.out.println("Type 'exit' when ready to quit): ");

        while (true) {
            Player player = players.get(turn % players.size());
            System.out.println("\nIt's " + player.getName() + "'s turn!");

            notifyEvent("turnStarted", Map.of("player", player.getName(), "turnIndex", turn));

            List<String> categories = questionBank.getCategories();
            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i));
            }

            System.out.print("Choose a category (or type 'exit' to quit): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Game ended early. Goodbye!");
                notifyEvent("gameEnded", this);
                break; // exits the main game loop
            }

            int catChoice;
            try {
                catChoice = Integer.parseInt(input); // convert to int if valid
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number or 'exit'.");
                continue;
            }

            if (catChoice < 1 || catChoice > categories.size()) {
                System.out.println("Invalid category choice. Try again.");
                continue;
            }

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
            int qChoice;
            try {
                qChoice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid question choice.");
                continue;
            }

            if (qChoice < 1 || qChoice > available.size()) {
                System.out.println("Invalid question choice.");
                continue;
            }

            Question q = available.get(qChoice - 1);

            System.out.println("\n" + q);
            System.out.print("Your answer (A/B/C/D): ");
            String ans = sc.nextLine();

            boolean correct = q.checkAnswer(ans);
            if (correct) {
                System.out.println("Correct!");
                player.addScore(q.getValue());
            } else {
                System.out.println("Incorrect! The correct answer was: " + q.getCorrectAnswer());
                // player.subtractScore(q.getValue());
            }

            // log the turn and notify observers about questionAnswered
            Turn turnRecord = new Turn(
                    player.getName(),
                    category,
                    q.getText(),
                    ans,
                    correct,
                    q.getValue(),
                    player.getScore());
            gameplayLog.add(turnRecord);

            notifyEvent("questionAnswered", turnRecord);

            q.setAnswered(true);
            System.out.println(player.getName() + " score: " + player.getScore());
            turn++;

            // check if all questions are answered; if so end game naturally
            boolean allDone = true;
            for (List<Question> list : questionBank.getCategoryMap().values()) {
                for (Question qq : list) {
                    if (!qq.isAnswered()) {
                        allDone = false;
                        break;
                    }
                }
                if (!allDone)
                    break;
            }
            if (allDone) {
                System.out.println("\nAll questions have been answered. Game over!");
                notifyEvent("gameEnded", this);
                break;
            }
        }

        // don't close System.in (scanner) to avoid interfering with other runs;
        // optional cleanup if necessary
    }
}
