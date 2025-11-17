/**
 * @author Shania Siew
 * Factory class to obtain different WriteStrategy implementations based on file type.
 */
public class WriteStrategyFactory {
    /**
     * Returns a WriteStrategy instance based on the provided file type.
     * Supported file types are CSV, PDF, TXT, and DOCX.
     * If the provided file type is not supported, a CSVWriteStrategy instance is returned.
     * @param type the file type to obtain a WriteStrategy for
     * @return a WriteStrategy instance based on the provided file type
     */
    public static WriteStrategy getStrategy(String type) {
        switch (type.toLowerCase()) {
            case "csv":
                return new CSVWriteStrategy();
            case "pdf":
                return new PDFWriteStrategy();
            case "txt":
                return new TXTWriteStrategy();
            case "docx":
                return new DOCXWriteStrategy();
            default:
                return new CSVWriteStrategy();
        }
    }
}
