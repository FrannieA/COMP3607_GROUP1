import java.util.ArrayList;
import java.util.List;

/**
 * @author Shania Siew
 * Represents a category that contains a collection of questions.
 * Each category has a name and can store multiple {@link Question} objects.
 */
public class Category {
    private String name;
    private List<Question> questions = new ArrayList<>();

    /**
     * Constructs a Category with the given name.
     * @param name the name of the category
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Adds a question to the category.
     * @param q the {@link Question} to add
     */
    public void addQuestion(Question q) {
        questions.add(q);
    }

    /**
     * Returns a list of all questions in this category.
     * @return a list of {@link Question} objects
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Returns the name of this category.
     * @return the name of this category
     */
    public String getName() {
        return name;
    }

}
