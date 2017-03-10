package jpmc.markup;

import java.util.HashMap;
import java.util.Map;

public class CorporateMarkUpService implements BaseMarkUp {
    private final int[] corporateRange;
    private final Map<Integer, Integer> corporateMarkUp;

    public CorporateMarkUpService() {
        corporateRange = new int[3];
        corporateRange[0] = 0;
        corporateRange[1] = 1000000;
        corporateRange[2] = 3000000;

        corporateMarkUp = new HashMap<Integer, Integer>();
        corporateMarkUp.put(0, 15);
        corporateMarkUp.put(1, 10);
        corporateMarkUp.put(2, 5);
    }

    @Override
    public int getMarkUp(double amount) {
        if (amount == 0) {
            return corporateMarkUp.get(0);
        }
        int start = 0;
        int end = corporateRange.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (corporateRange[mid] == amount) {
                return corporateMarkUp.get(mid - 1);
            } else if (amount < corporateRange[mid]) {
                end = mid - 1;
            } else if (amount > corporateRange[mid]) {
                start = mid + 1;
            }
        }

        return corporateMarkUp.get(end);
    }
}
