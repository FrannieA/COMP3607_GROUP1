
import java.util.*;

/**
 * @author Shania Siew
 * A collection of questions organized by category.
 * <p>
 * This class maintains a mapping of category names to lists of
 * {@link Question} objects, allowing for efficient retrieval and
 * organization of questions based on their categories.
 * </p>
 */
public class QuestionBank {
    private Map<String, List<Question>> categoryMap = new LinkedHashMap<>();


    /**
     * Adds a question to the question bank.
     * If the question does not have a category, it is placed in the "General" category.
     * The question bank is organized as a mapping of category names to lists of question objects.
     * This method adds a question to its corresponding category list if that category exists, or
     * creates a new category list if it does not exist.
     * @param q the question to add to the question bank
     */
    public void addQuestion(Question q) {
        String category = q.getCategory() == null ? "General" : q.getCategory();
        categoryMap.putIfAbsent(category, new ArrayList<>());
        categoryMap.get(category).add(q);
    }


    /**
     * Returns a mapping of category names to lists of question objects.
     * The mapping returned is a read-only view of the internal mapping.
     * @return a mapping of category names to lists of question objects
     */
    public Map<String, List<Question>> getCategoryMap() {
        return categoryMap;
    }

    /**
     * Returns a list of all categories in the question bank.
     * The list returned is a new instance and does not reflect any changes to the question bank.
     * @return a list of all categories in the question bank
     */
    public List<String> getCategories() {
        return new ArrayList<>(categoryMap.keySet());
    }

    /**
     * Returns a list of all questions in the given category.
     * The list returned is a read-only view of the internal question bank.
     * If the category does not exist, an empty list is returned.
     * @param category the category to retrieve questions from
     * @return a list of all questions in the given category
     */
    public List<Question> getQuestionsByCategory(String category) {
        return categoryMap.getOrDefault(category, new ArrayList<>());
    }
}
