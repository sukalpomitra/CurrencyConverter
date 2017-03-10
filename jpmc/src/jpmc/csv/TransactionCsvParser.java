package jpmc.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import jpmc.Transaction;

public class TransactionCsvParser implements BaseCsvParser {

    private final String csvFile;
    private final String csvSplitter;
    private String line;
    private BufferedReader br = null;
    private List<Object> parsedTransactions;
    private final boolean skipHeader;

    public TransactionCsvParser(String csvFile, String csvSplitter,
            String line, boolean skipHeader) {
        this.csvFile = csvFile;
        this.csvSplitter = csvSplitter;
        this.line = line;
        this.skipHeader = skipHeader;
    }

    @Override
    public List<Object> readCsv() {
        try {
            parsedTransactions = new ArrayList<Object>();
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(csvFile).getFile());
            br = new BufferedReader(new FileReader(file));
            int lineCounter = 0;
            while ((line = br.readLine()) != null) {
                lineCounter++;
                if (lineCounter == 1 && skipHeader) {
                    continue;
                }
                Transaction tx = new Transaction();

                // use comma as separator
                String[] transaction = line.split(csvSplitter);

                tx.setBaseCurrency(transaction[0]);
                tx.setWantedCurrency(transaction[1]);
                tx.setAmountInBaseCurrency(Double.parseDouble(transaction[2]));
                tx.setClientType(transaction[3]);
                String time = transaction[4];
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                try {
                    Date date = sdf.parse(time);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    tx.setTimeInMilliseconds(date.getTime());
                    parsedTransactions.add(tx);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return parsedTransactions;
    }

}
