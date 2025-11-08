package src.main.java;

public class WriteStrategyFactory {
    public static WriteStrategy getStrategy(String type) {
        switch (type.toLowerCase()) {
            case "csv":
                return new CSVWriteStrategy();
            case "pdf":
                return new PDFWriteStrategy();
            case "txt":
                return new TXTWriteStrategy();
            default:
                throw new IllegalArgumentException("Unknown write strategy: " + type);
        }
    }
}
