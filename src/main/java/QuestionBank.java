package src.main.java;

import java.util.*;

public class QuestionBank {
    private Map<String, List<Question>> categoryMap = new LinkedHashMap<>();

    public void addQuestion(Question q) {
        String category = q.getCategory() == null ? "General" : q.getCategory();
        categoryMap.putIfAbsent(category, new ArrayList<>());
        categoryMap.get(category).add(q);
    }

    public Map<String, List<Question>> getCategoryMap() {
        return categoryMap;
    }

    public List<String> getCategories() {
        return new ArrayList<>(categoryMap.keySet());
    }

    public List<Question> getQuestionsByCategory(String category) {
        return categoryMap.getOrDefault(category, new ArrayList<>());
    }
}
