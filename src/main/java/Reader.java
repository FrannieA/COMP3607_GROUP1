
/** 
 * @author Shania Siew
 * Interface for reading question banks from files.
 */
public interface Reader {
    QuestionBank read(String filePath) throws Exception;
}
