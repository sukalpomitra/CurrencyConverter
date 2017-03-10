package jpmc;
import static org.junit.Assert.assertTrue;
import jpmc.CurrencyConverter;
import jpmc.utilities.MarkUpTypes;

import org.junit.Test;

public class CalculateIndividualMarkUpTest {

    @Test
    public void shouldCalculateMarkUpAsForty() {
        // given
        CurrencyConverter converter = new CurrencyConverter();

        // when
        int markUp = converter.calculateMarkUp(8000,
                MarkUpTypes.INDIVIDUAL_MARKUP_TYPE);

        // then
        assertTrue(markUp == 40);

        // when
        markUp = converter.calculateMarkUp(5000,
                MarkUpTypes.INDIVIDUAL_MARKUP_TYPE);

        // then
        assertTrue(markUp == 40);

        // when
        markUp = converter.calculateMarkUp(0,
                MarkUpTypes.INDIVIDUAL_MARKUP_TYPE);

        // then
        assertTrue(markUp == 40);
    }

    @Test
    public void shouldCalculateMarkUpAsThirtyFive() {
        // given
        CurrencyConverter converter = new CurrencyConverter();

        // when
        int markUp = converter.calculateMarkUp(15000,
                MarkUpTypes.INDIVIDUAL_MARKUP_TYPE);

        // then
        assertTrue(markUp == 35);

        // when
        markUp = converter.calculateMarkUp(20000,
                MarkUpTypes.INDIVIDUAL_MARKUP_TYPE);

        // then
        assertTrue(markUp == 35);
    }

    @Test
    public void shouldCalculateMarkUpAsThirty() {
        // given
        CurrencyConverter converter = new CurrencyConverter();

        // when
        int markUp = converter.calculateMarkUp(35000,
                MarkUpTypes.INDIVIDUAL_MARKUP_TYPE);

        // then
        assertTrue(markUp == 30);

        // when
        markUp = converter.calculateMarkUp(25000,
                MarkUpTypes.INDIVIDUAL_MARKUP_TYPE);

        // then
        assertTrue(markUp == 30);
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
