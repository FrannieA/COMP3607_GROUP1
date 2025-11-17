/**
 * @author Shania Siew
 * Class representing a question in the game.
 * Each question has a category, value, text, multiple-choice options, and a correct answer.
 * It also tracks whether the question has been answered.
 */
public class Question {
    private String category;
    private int value;
    private String text;
    private String[] options;
    private String correctAnswer;
    private boolean answered = false;

    /**
     * Constructs a Question with the given parameters. 
     * @param value
     * @param text
     * @param options
     * @param correctAnswer
     */
    public Question(int value, String text, String[] options, String correctAnswer) {
        this.value = value;
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer.trim().toUpperCase();
    }

    /**
     * Constructs a Question with the given parameters.
     * @param category
     * @param value
     * @param text
     * @param optionA
     * @param optionB
     * @param optionC
     * @param optionD
     * @param correctAnswer
     */
    public Question(String category, int value, String text,
            String optionA, String optionB, String optionC, String optionD, char correctAnswer) {
        this.category = category;
        this.value = value;
        this.text = text;
        this.options = new String[] { optionA, optionB, optionC, optionD };
        this.correctAnswer = String.valueOf(Character.toUpperCase(correctAnswer));
    }

    /**
     * Checks if the given answer is correct.
     * If the answer is a single letter (A-D), it is checked directly.
     * If the answer is a number, it is checked against the correct index (1-4).
     * If the answer is invalid, false is returned.
     * @param answer the answer to check
     * @return true if the answer is correct, false otherwise
     */
    public boolean checkAnswer(String answer) {
        answer = answer.trim().toUpperCase();
        if (answer.length() == 1 && Character.isLetter(answer.charAt(0))) {
            return answer.equals(correctAnswer);
        }
        try {
            int idx = Integer.parseInt(answer);
            int correctIndex = correctAnswer.charAt(0) - 'A' + 1;
            return idx == correctIndex;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns the value of the question, which is used to determine the difficulty
     * of the question.
     * @return the value of the question
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the text of the question.
     * @return the text of the question
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the options of the question as an array of strings.
     * The order of the options is A, B, C, D.
     * @return the options of the question
     */
    public String[] getOptions() {
        return options;
    }

    /**
     * Returns the correct answer to the question as a single uppercase letter (A, B, C, or D).
     * @return the correct answer to the question
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Returns the category of this question.
     * @return the category of this question
     */
    public String getCategory() {
        return category;
    }

    /**
     * Returns whether the question has been answered.
     * @return true if the question has been answered, false otherwise
     */
    public boolean isAnswered() {
        return answered;
    }

    /**
     * Sets whether the question has been answered.
     * @param a true if the question has been answered, false otherwise
     */
    public void setAnswered(boolean a) {
        this.answered = a;
    }

    /**
     * Returns a string representation of this question, which includes the category, value,
     * text, and options of the question. The format is as follows:
     * <pre>
     * [Category] (Value pts)
     * QuestionText
     * A. OptionA
     * B. OptionB
     * C. OptionC
     * D. OptionD
     * </pre>
     * @return a string representation of this question
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(category).append("] (").append(value).append(" pts)\n");
        sb.append(text).append("\n");
        char opt = 'A';
        for (String o : options) {
            sb.append(opt++).append(". ").append(o).append("\n");
        }
        return sb.toString();
    }
}
