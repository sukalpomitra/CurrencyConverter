package jpmc.factory;

import jpmc.markup.BaseMarkUp;
import jpmc.markup.CorporateMarkUpService;
import jpmc.markup.IndividualMarkUpService;
import jpmc.utilities.MarkUpTypes;

public class MarkUpFactory {

    public BaseMarkUp acquireService(String markUpType) {
        if (markUpType.equals(MarkUpTypes.INDIVIDUAL_MARKUP_TYPE)) {
            return new IndividualMarkUpService();
        } else {
            return new CorporateMarkUpService();
        }
    }
}
