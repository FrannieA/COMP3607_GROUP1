package src.main.java;

// Category.java
import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Question> questions = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }

}
