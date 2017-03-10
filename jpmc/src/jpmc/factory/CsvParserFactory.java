package jpmc.factory;

import jpmc.csv.BaseCsvParser;
import jpmc.csv.RatesCsvParser;
import jpmc.csv.TransactionCsvParser;
import jpmc.utilities.CsvTypes;

public class CsvParserFactory {

    public BaseCsvParser acquireService(String csvType, String csvFile,
            String csvSplitter, String line) {
        if (csvType.equals(CsvTypes.RATES_CSV)) {
            return new RatesCsvParser(csvFile, csvSplitter, line, false);
        } else {
            return new TransactionCsvParser(csvFile, csvSplitter, line, true);
        }
    }
}
