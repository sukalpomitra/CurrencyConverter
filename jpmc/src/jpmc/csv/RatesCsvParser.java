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

import jpmc.Rates;

public class RatesCsvParser implements BaseCsvParser {

    private final String csvFile;
    private final String csvSplitter;
    private String line;
    private BufferedReader br = null;
    private List<Object> parsedRates;
    private final boolean skipHeader;

    public RatesCsvParser(String csvFile, String csvSplitter, String line,
            boolean skipHeader) {
        this.csvFile = csvFile;
        this.csvSplitter = csvSplitter;
        this.line = line;
        this.skipHeader = skipHeader;
    }

    @Override
    public List<Object> readCsv() {
        try {
            parsedRates = new ArrayList<Object>();
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(csvFile).getFile());
            int lineCounter = 0;
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                lineCounter++;
                if (lineCounter == 1 && skipHeader) {
                    continue;
                }
                Rates rates = new Rates();
                // use comma as separator
                String[] rate = line.split(csvSplitter);
                rates.setBaseCurrency(rate[0]);
                rates.setWantedCurrency(rate[1]);
                rates.setStandardRate(Double.parseDouble(rate[2]));
                String time = rate[3];
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                try {
                    Date date = sdf.parse(time);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    rates.setMilliSeconds(calendar.getTimeInMillis());
                    parsedRates.add(rates);
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
        return parsedRates;
    }

}
