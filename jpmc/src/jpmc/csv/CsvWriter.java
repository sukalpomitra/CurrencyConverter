package jpmc.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jpmc.ConvertedCurrency;

public class CsvWriter {

    private final String csvFile;
    private final String csvSplitter;
    private final String line;

    public CsvWriter(String csvFile, String csvSplitter, String line) {
        this.csvFile = csvFile;
        this.csvSplitter = csvSplitter;
        this.line = line;
    }

    public void writeCsv(List<ConvertedCurrency> conversions) {
        ClassLoader classLoader = getClass().getClassLoader();
        File ratesFile = new File(classLoader.getResource("rates.csv")
                .getFile());
        File parentDirectory = ratesFile.getParentFile();
        File file = new File(parentDirectory, csvFile);
        try {
            FileWriter writer = new FileWriter(file);

            writeLine(writer, Arrays.asList("BaseCurrency", "WantedCurrency",
                    "AmountInBaseCurrency", "StandardRate", "FinalRate",
                    "ProfitInWantedCurrency", "ProfitInSGD"), csvSplitter);
            for (ConvertedCurrency conversion : conversions) {
                DecimalFormat rateFormat = new DecimalFormat("#.####");
                DecimalFormat amtFormat = new DecimalFormat("#.##");
                List<String> list = new ArrayList<String>();
                list.add(conversion.getBaseCurrency());
                list.add(conversion.getWantedCurrency());
                list.add(conversion.getAmountInBaseCurrency() + "");
                list.add(conversion.getStandardRate() + "");
                list.add(rateFormat.format(conversion.getFinalRate()) + "");
                list.add(amtFormat.format(conversion
                        .getProfitInWantedCurrency()) + "");
                list.add(amtFormat.format(conversion.getProfitInSGD()) + "");

                writeLine(writer, list, csvSplitter);
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void writeLine(Writer w, List<String> values,
            String csvSplitter) throws IOException {

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            sb.append(csvSplitter);
            sb.append(followCVSformat(value));

        }
        sb.append("\n");
        w.append(sb.toString());

    }

    private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }

}
