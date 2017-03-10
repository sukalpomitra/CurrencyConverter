package jpmc;

public class Transaction {

    private String baseCurrency;
    private String wantedCurrency;
    private double amountInBaseCurrency;
    private String clientType;
    private long timeInMilliseconds;

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getWantedCurrency() {
        return wantedCurrency;
    }

    public void setWantedCurrency(String wantedCurrency) {
        this.wantedCurrency = wantedCurrency;
    }

    public double getAmountInBaseCurrency() {
        return amountInBaseCurrency;
    }

    public void setAmountInBaseCurrency(double amountInBaseCurrency) {
        this.amountInBaseCurrency = amountInBaseCurrency;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public void setTimeInMilliseconds(long timeInMilliseconds) {
        this.timeInMilliseconds = timeInMilliseconds;
    }

}
