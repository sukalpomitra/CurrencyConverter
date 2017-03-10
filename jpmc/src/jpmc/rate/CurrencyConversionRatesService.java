package jpmc.rate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jpmc.Rates;
import jpmc.Transaction;
import jpmc.utilities.Delimiters;

public class CurrencyConversionRatesService {

    private final Map<String, Double> rateMap;
    private final Map<String, List<Long>> rateTimeRange;

    public CurrencyConversionRatesService() {
        rateMap = new HashMap<String, Double>();
        rateTimeRange = new HashMap<String, List<Long>>();
    }

    public void setConversionRatesProperties(List<Object> rates) {
        for (Object object : rates) {
            Rates rate = (Rates) object;
            rateMap.put(formKey(rate), rate.getStandardRate());
            String timeRangeKey = formTimeRangeKey(rate);
            if (rateTimeRange.containsKey(timeRangeKey)) {
                List<Long> timeRange = rateTimeRange.get(timeRangeKey);
                timeRange.add(rate.getMilliSeconds());
            } else {
                List<Long> timeRange = new ArrayList<Long>();
                timeRange.add(rate.getMilliSeconds());
                rateTimeRange.put(timeRangeKey, timeRange);
            }
        }
    }

    public String formKey(Rates rate) {
        return rate.getBaseCurrency() + Delimiters.RATE_MAP_DELIMITER
                + rate.getWantedCurrency() + Delimiters.RATE_MAP_DELIMITER
                + rate.getMilliSeconds();
    }

    public String formTimeRangeKey(Rates rate) {
        return rate.getBaseCurrency() + Delimiters.RATE_MAP_DELIMITER
                + rate.getWantedCurrency();
    }

    public Double getRate(Transaction transaction) {
        String key = transaction.getBaseCurrency()
                + Delimiters.RATE_MAP_DELIMITER
                + transaction.getWantedCurrency();
        List<Long> timeRangeList = rateTimeRange.get(key);
        Long[] timeRange = timeRangeList
                .toArray(new Long[timeRangeList.size()]);
        int start = 0;
        int end = timeRange.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (timeRange[mid] == transaction.getTimeInMilliseconds()) {
                return rateMap.get(transaction.getBaseCurrency()
                        + Delimiters.RATE_MAP_DELIMITER
                        + transaction.getWantedCurrency()
                        + Delimiters.RATE_MAP_DELIMITER + timeRange[mid]);
            } else if (transaction.getTimeInMilliseconds() < timeRange[mid]) {
                end = mid - 1;
            } else if (transaction.getTimeInMilliseconds() > timeRange[mid]) {
                start = mid + 1;
            }
        }

        return rateMap.get(transaction.getBaseCurrency()
                + Delimiters.RATE_MAP_DELIMITER
                + transaction.getWantedCurrency()
                + Delimiters.RATE_MAP_DELIMITER + timeRange[end + 1]);
    }

}
