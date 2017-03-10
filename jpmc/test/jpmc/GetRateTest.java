package jpmc;

import static org.junit.Assert.assertTrue;

import java.util.List;

import jpmc.utilities.CsvTypes;

import org.junit.Test;

public class GetRateTest {

    @Test
    public void shouldGetRate() {
        // given
        CurrencyConverter converter = new CurrencyConverter();
        converter.configureRates(converter.readCsv(CsvTypes.RATES_CSV,
                "rates.csv"));

        // when
        List<ConvertedCurrency> conversions = converter
                .getCurrencyConversions(converter.readCsv(CsvTypes.TX_CSV,
                        "transactions.csv"));

        // then
        assertTrue(!conversions.isEmpty());
        assertTrue(conversions.size() == 5);
        assertTrue(conversions.get(0).getStandardRate() == 0.2012);
    }
}
