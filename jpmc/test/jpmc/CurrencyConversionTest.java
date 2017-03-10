package jpmc;

import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;
import java.util.List;

import jpmc.utilities.CsvTypes;

import org.junit.Test;

public class CurrencyConversionTest {

    @Test
    public void shouldGetConvertedCurrency() {
        // given
        CurrencyConverter converter = new CurrencyConverter();
        converter.configureRates(converter.readCsv(CsvTypes.RATES_CSV,
                "rates.csv"));
        DecimalFormat rateFormat = new DecimalFormat("#.####");
        DecimalFormat amtFormat = new DecimalFormat("#.##");

        // when
        List<ConvertedCurrency> conversions = converter
                .getCurrencyConversions(converter.readCsv(CsvTypes.TX_CSV,
                        "transactions.csv"));

        // then
        assertTrue(!conversions.isEmpty());
        assertTrue(conversions.size() == 5);
        assertTrue(conversions.get(0).getStandardRate() == 0.2012);
        assertTrue(rateFormat.format(conversions.get(0).getFinalRate()).equals(
                "0.2004"));
        assertTrue(amtFormat.format(
                conversions.get(0).getProfitInWantedCurrency()).equals("32.19"));
    }
}
