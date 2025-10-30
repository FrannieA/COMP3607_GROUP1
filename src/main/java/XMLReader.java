package src.main.java;

public class XMLReader implements Reader {
    @Override
    public QuestionBank read(String filePath) throws Exception {
        // Implement JSON reading logic here
        // For demonstration, returning an empty QuestionBank
        return new QuestionBank();
    }
}