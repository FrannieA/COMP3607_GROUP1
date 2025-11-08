// package src.main.java;

// public class Question {
//     private int value;
//     private String text;
//     private String[] options;
//     private String correctAnswer; // "A", "B", "C", or "D"
//     private boolean answered = false;

//     public Question(int value, String text, String[] options, String correctAnswer) {
//         this.value = value;
//         this.text = text;
//         this.options = options;
//         this.correctAnswer = correctAnswer.toUpperCase();
//     }

//     public boolean checkAnswer(String answer) {
//         answer = answer.trim().toUpperCase();

//         // Letter input
//         if (answer.length() == 1 && Character.isLetter(answer.charAt(0))) {
//             return answer.equals(correctAnswer);
//         }

//         // Number input (1-4)
//         try {
//             int idx = Integer.parseInt(answer);
//             int correctIndex = correctAnswer.charAt(0) - 'A' + 1;
//             return idx == correctIndex;
//         } catch (NumberFormatException e) {
//             return false;
//         }
//     }

//     public int getValue() {
//         return value;
//     }

//     public String getText() {
//         return text;
//     }

//     public String[] getOptions() {
//         return options;
//     }

//     public boolean isAnswered() {
//         return answered;
//     }

//     public void setAnswered(boolean answered) {
//         this.answered = answered;
//     }

//     public String getCorrectAnswer() {
//         return correctAnswer;
//     }

//     public boolean isUsed() {
//         return answered;
//     }

//     public void setUsed(boolean used) {
//         this.answered = used;
//     }

//     public String toString() {
//         StringBuilder sb = new StringBuilder();
//         sb.append("Value: ").append(value).append("\n");
//         sb.append("Question: ").append(text).append("\n");
//         char optionLabel = 'A';
//         for (String option : options) {
//             sb.append(optionLabel).append(". ").append(option).append("\n");
//             optionLabel++;
//         }
//         sb.append("Correct Answer: ").append(correctAnswer).append("\n");
//         return sb.toString();
//     }

//     public Category getCategory() {
//         return null;
//     }

//     public void display() {
//         System.out.println(this.toString());
//     }
// }

package src.main.java;

public class Question {
    private String category;
    private int value;
    private String text;
    private String[] options;
    private String correctAnswer;
    private boolean answered = false;

    public Question(int value, String text, String[] options, String correctAnswer) {
        this.value = value;
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer.trim().toUpperCase();
    }

    public Question(String category, int value, String text,
            String optionA, String optionB, String optionC, String optionD, char correctAnswer) {
        this.category = category;
        this.value = value;
        this.text = text;
        this.options = new String[] { optionA, optionB, optionC, optionD };
        this.correctAnswer = String.valueOf(Character.toUpperCase(correctAnswer));
    }

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

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean a) {
        this.answered = a;
    }

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
