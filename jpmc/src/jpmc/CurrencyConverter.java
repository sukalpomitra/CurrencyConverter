package jpmc;

import java.util.ArrayList;
import java.util.List;

import jpmc.csv.BaseCsvParser;
import jpmc.csv.CsvWriter;
import jpmc.factory.CsvParserFactory;
import jpmc.factory.MarkUpFactory;
import jpmc.markup.BaseMarkUp;
import jpmc.rate.CurrencyConversionRatesService;
import jpmc.utilities.CsvTypes;
import jpmc.utilities.Delimiters;

public class CurrencyConverter {

    private final MarkUpFactory markUpfactory;
    private final CsvParserFactory csvParserFactory;
    private final CurrencyConversionRatesService rateService;
    private CsvWriter writer;

    public CurrencyConverter() {
        markUpfactory = new MarkUpFactory();
        csvParserFactory = new CsvParserFactory();
        rateService = new CurrencyConversionRatesService();
    }

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        converter.configureRates(converter.readCsv(CsvTypes.RATES_CSV,
                "rates.csv"));
        List<ConvertedCurrency> conversions = converter
                .getCurrencyConversions(converter.readCsv(CsvTypes.TX_CSV,
                        "transactions.csv"));
        converter.generatePnlReport(conversions);
    }

    public int calculateMarkUp(double amount, String markUpType) {
        BaseMarkUp markUp = markUpfactory.acquireService(markUpType);
        return markUp.getMarkUp(amount);
    }

    public List<Object> readCsv(String csvType, String file) {
        BaseCsvParser parser = csvParserFactory.acquireService(csvType, file,
                Delimiters.COMMA_SEPERATORS, "");
        return parser.readCsv();
    }

    public void configureRates(List<Object> rates) {
        rateService.setConversionRatesProperties(rates);
    }

    public void generatePnlReport(List<ConvertedCurrency> conversions) {
        writer = new CsvWriter("report.csv", Delimiters.COMMA_SEPERATORS, "");
        writer.writeCsv(conversions);
    }

    public List<ConvertedCurrency> getCurrencyConversions(
            List<Object> transactions) {
        List<ConvertedCurrency> conversions = new ArrayList<ConvertedCurrency>();
        for (Object object : transactions) {
            Transaction transaction = (Transaction) object;
            ConvertedCurrency conversion = new ConvertedCurrency();
            conversion.setStandardRate(rateService.getRate(transaction));
            conversion.setAmountInBaseCurrency(transaction
                    .getAmountInBaseCurrency());
            conversion.setBaseCurrency(transaction.getBaseCurrency());
            conversion.setWantedCurrency(transaction.getWantedCurrency());
            double rate = conversion.getStandardRate();
            if (!transaction.getBaseCurrency().equals("USD")) {
                Transaction usdTransaction = new Transaction();
                usdTransaction.setBaseCurrency(transaction.getBaseCurrency());
                usdTransaction.setWantedCurrency("USD");
                usdTransaction.setTimeInMilliseconds(transaction
                        .getTimeInMilliseconds());
                rate = rateService.getRate(usdTransaction);
            }
            calculateFinalRate(conversion, rate, transaction.getClientType());
            calculateProfitInWantedCurrency(conversion);
            if (!conversion.getWantedCurrency().equals("SGD")) {
                calculateProfitInSGD(conversion, transaction);
            } else {
                conversion.setProfitInSGD(conversion
                        .getProfitInWantedCurrency());
            }
            conversions.add(conversion);
        }
        return conversions;
    }

    public void calculateFinalRate(ConvertedCurrency conversion, double rate,
            String markUpType) {
        double amount = conversion.getAmountInBaseCurrency() * rate;
        int markUp = calculateMarkUp(amount, markUpType);
        double bps = 1 - (markUp / 10000.0);
        conversion.setFinalRate(conversion.getStandardRate() * bps);
    }

    public void calculateProfitInWantedCurrency(ConvertedCurrency conversion) {
        double standardRate = conversion.getStandardRate();
        double finalRate = conversion.getFinalRate();
        double amount = conversion.getAmountInBaseCurrency();
        conversion.setProfitInWantedCurrency((standardRate - finalRate)
                * amount);
    }

    public void calculateProfitInSGD(ConvertedCurrency conversion,
            Transaction transaction) {
        Transaction sgdTransaction = new Transaction();
        sgdTransaction.setBaseCurrency(conversion.getWantedCurrency());
        sgdTransaction.setWantedCurrency("SGD");
        sgdTransaction.setTimeInMilliseconds(transaction
                .getTimeInMilliseconds());
        double rate = rateService.getRate(sgdTransaction);
        conversion
                .setProfitInSGD(conversion.getProfitInWantedCurrency() * rate);
    }
}
