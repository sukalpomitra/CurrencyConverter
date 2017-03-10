package jpmc;


import static org.junit.Assert.assertTrue;
import jpmc.CurrencyConverter;
import jpmc.utilities.MarkUpTypes;

import org.junit.Test;

public class CalculateCorporateMarkUpTest {

    @Test
    public void shouldCalculateMarkUpAsFifteen() {
        // given
        CurrencyConverter converter = new CurrencyConverter();

        // when
        int markUp = converter.calculateMarkUp(0,
                MarkUpTypes.CORPORATE_MARKUP_TYPE);

        // then
        assertTrue(markUp == 15);

        // when
        markUp = converter.calculateMarkUp(100000,
                MarkUpTypes.CORPORATE_MARKUP_TYPE);

        // then
        assertTrue(markUp == 15);

        // when
        markUp = converter.calculateMarkUp(90000,
                MarkUpTypes.CORPORATE_MARKUP_TYPE);

        // then
        assertTrue(markUp == 15);
    }

    @Test
    public void shouldCalculateMarkUpAsTen() {
        // given
        CurrencyConverter converter = new CurrencyConverter();

        // when
        int markUp = converter.calculateMarkUp(1000001,
                MarkUpTypes.CORPORATE_MARKUP_TYPE);

        // then
        assertTrue(markUp == 10);

        // when
        markUp = converter.calculateMarkUp(3000000,
                MarkUpTypes.CORPORATE_MARKUP_TYPE);

        // then
        assertTrue(markUp == 10);
    }

    @Test
    public void shouldCalculateMarkUpAsFive() {
        // given
        CurrencyConverter converter = new CurrencyConverter();

        // when
        int markUp = converter.calculateMarkUp(3000001,
                MarkUpTypes.CORPORATE_MARKUP_TYPE);

        // then
        assertTrue(markUp == 5);

        // when
        markUp = converter.calculateMarkUp(9000000,
                MarkUpTypes.CORPORATE_MARKUP_TYPE);

        // then
        assertTrue(markUp == 5);
    }

    @Test
    public void shouldCalculateMarkUpAsTwentyFive() {
        // given
        CurrencyConverter converter = new CurrencyConverter();

        // when
        int markUp = converter.calculateMarkUp(1000000,
                MarkUpTypes.INDIVIDUAL_MARKUP_TYPE);

        // then
        assertTrue(markUp == 25);
    }
}
