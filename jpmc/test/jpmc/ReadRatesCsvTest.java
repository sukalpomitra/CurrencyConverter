package jpmc;

import static org.junit.Assert.assertTrue;

import java.util.List;

import jpmc.utilities.CsvTypes;

import org.junit.Test;

public class ReadRatesCsvTest {

    @Test
    public void shouldCalculateMarkUpAsFifteen() {
        // given
        CurrencyConverter converter = new CurrencyConverter();

        // when
        List<Object> rates = converter.readCsv(CsvTypes.RATES_CSV, "rates.csv");

        // then
        assertTrue(!rates.isEmpty());
        assertTrue(rates.size() == 28);
        assertTrue(((Rates) rates.get(0)).getBaseCurrency().equals("CNY"));
    }
}
