package jpmc;

import static org.junit.Assert.assertTrue;

import java.util.List;

import jpmc.utilities.CsvTypes;

import org.junit.Test;

public class ReadTransactionCsvTest {

    @Test
    public void shouldCalculateMarkUpAsFifteen() {
        // given
        CurrencyConverter converter = new CurrencyConverter();

        // when
        List<Object> transactions = converter.readCsv(CsvTypes.TX_CSV,
                "transactions.csv");

        // then
        assertTrue(!transactions.isEmpty());
        assertTrue(transactions.size() == 5);
        assertTrue(((Transaction) transactions.get(0)).getBaseCurrency()
                .equals("CNY"));
    }
}
