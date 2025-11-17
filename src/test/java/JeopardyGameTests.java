import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class JeopardyGameTests {

    // ===== Player Tests =====
    @Test
    void testPlayerAddScore() {
        Player p = new Player("Alice");
        p.addScore(100);
        assertEquals(100, p.getScore());
    }

    @Test
    void testPlayerGetName() {
        Player p = new Player("Alice");
        assertEquals("Alice", p.getName());
    }

    // ===== Question Tests =====
    @Test
    void testCorrectLetterAnswer() {
        Question q = new Question("Category", 100, "Test?",
                "A1", "A2", "A3", "A4", 'A');
        assertTrue(q.checkAnswer("A"));
    }

    @Test
    void testIncorrectLetterAnswer() {
        Question q = new Question("Category", 100, "Test?",
                "A1", "A2", "A3", "A4", 'B');
        assertFalse(q.checkAnswer("A"));
    }

    @Test
    void testNumberAnswer() {
        Question q = new Question("Category", 100, "Test?",
                "A1", "A2", "A3", "A4", 'C');
        assertTrue(q.checkAnswer("3"));
    }

    @Test
    void testSetAnswered() {
        Question q = new Question("Cat", 100, "Test", "A", "B", "C", "D", 'A');
        q.setAnswered(true);
        assertTrue(q.isAnswered());
    }

    // ===== QuestionBank Tests =====
    @Test
    void testAddAndRetrieveQuestions() {
        QuestionBank bank = new QuestionBank();
        Question q = new Question("Math", 100, "1+1?", "1", "2", "3", "4", 'B');
        bank.addQuestion(q);

        assertEquals(1, bank.getQuestionsByCategory("Math").size());
        assertEquals("1+1?", bank.getQuestionsByCategory("Math").get(0).getText());
    }

    @Test
    void testGetCategories() {
        QuestionBank bank = new QuestionBank();
        bank.addQuestion(new Question("Sci", 100, "Q", "A", "B", "C", "D", 'A'));
        bank.addQuestion(new Question("Math", 100, "Q", "A", "B", "C", "D", 'B'));
        assertEquals(2, bank.getCategories().size());
    }

    // ===== Logger (CSV Log Writer) =====
    @Test
    void testLoggerCreatesCSVLog() throws Exception {
        Logger logger = new Logger();
        Game game = new Game();
        game.addPlayer(new Player("Alice"));
        game.getGameplayLog().add(
                new Turn("Alice", "Math", "1+1?", "B", true, 100, 100));
        logger.update("gameEnded", game);
        File logFile = new File("game_event_log.csv");
        assertTrue(logFile.exists());
        assertTrue(logFile.length() > 0);
    }

    // ===== ReportWriter (TXT / PDF / DOCX) =====
    @Test
    void testTXTReportWriter() {
        Game game = new Game(List.of(new Player("Alice")), new QuestionBank());
        WriteStrategy strategy = new TXTWriteStrategy();
        ReportWriter writer = new ReportWriter(strategy, "test_report.txt");

        writer.update("gameEnded", game);

        assertTrue(new File("test_report.txt").exists());
    }

    @Test
    void testPDFReportWriter() {
        Game game = new Game(List.of(new Player("Alice")), new QuestionBank());
        WriteStrategy strategy = new PDFWriteStrategy();
        ReportWriter writer = new ReportWriter(strategy, "test_report.pdf");

        writer.update("gameEnded", game);

        assertTrue(new File("test_report.pdf").exists());
    }

    @Test
    void testDOCXReportWriter() {
        Game game = new Game(List.of(new Player("Alice")), new QuestionBank());
        WriteStrategy strategy = new DOCXWriteStrategy();
        ReportWriter writer = new ReportWriter(strategy, "test_report.docx");

        writer.update("gameEnded", game);

        assertTrue(new File("test_report.docx").exists());
    }

    // ===== WriteStrategyFactory =====
    @Test
    void testFactoryProducesCorrectStrategies() {
        assertTrue(WriteStrategyFactory.getStrategy("txt") instanceof TXTWriteStrategy);
        assertTrue(WriteStrategyFactory.getStrategy("pdf") instanceof PDFWriteStrategy);
        assertTrue(WriteStrategyFactory.getStrategy("docx") instanceof DOCXWriteStrategy);
    }

    @Test
    void testFactoryDefaultsToCVS() {
        assertTrue(WriteStrategyFactory.getStrategy("unknown") instanceof CSVWriteStrategy);
    }

    // ===== File Readers =====
    @Test
    void testCSVReader() throws Exception {
        CSVReader reader = new CSVReader();
        QuestionBank bank = reader.read("src/test/resource/sample_game_CSV.csv");

        assertEquals(5, bank.getQuestionsByCategory("Variables & Data Types").size());
    }

    @Test
    void testJSONReader() throws Exception {
        JSONReader reader = new JSONReader();
        QuestionBank bank = reader.read("src/test/resource/sample_game_JSON.json");

        assertEquals(5, bank.getQuestionsByCategory("Variables & Data Types").size());
    }

    @Test
    void testXMLReader() throws Exception {
        XMLReader reader = new XMLReader();
        QuestionBank bank = reader.read("src/test/resource/sample_game_XML.xml");

        assertEquals(5, bank.getQuestionsByCategory("Variables & Data Types").size());
    }
}
