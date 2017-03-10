package jpmc.markup;

import java.util.HashMap;
import java.util.Map;

public class IndividualMarkUpService implements BaseMarkUp {
    private final int[] individualRange;
    private final Map<Integer, Integer> individualMarkUp;

    public IndividualMarkUpService() {
        individualRange = new int[4];
        individualRange[0] = 0;
        individualRange[1] = 8000;
        individualRange[2] = 20000;
        individualRange[3] = 35000;

        individualMarkUp = new HashMap<Integer, Integer>();
        individualMarkUp.put(0, 40);
        individualMarkUp.put(1, 35);
        individualMarkUp.put(2, 30);
        individualMarkUp.put(3, 25);
    }

    @Override
    public int getMarkUp(double amount) {
        if (amount == 0) {
            return individualMarkUp.get(0);
        }
        int start = 0;
        int end = individualRange.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (individualRange[mid] == amount) {
                return individualMarkUp.get(mid - 1);
            } else if (amount < individualRange[mid]) {
                end = mid - 1;
            } else if (amount > individualRange[mid]) {
                start = mid + 1;
            }
        }

        return individualMarkUp.get(end);

    }
}
