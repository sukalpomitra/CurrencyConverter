package jpmc;

public class ConvertedCurrency {

    private String baseCurrency;
    private String wantedCurrency;
    private double amountInBaseCurrency;
    private double standardRate;
    private double finalRate;
    private double profitInWantedCurrency;
    private double profitInSGD;

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

    public double getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(double standardRate) {
        this.standardRate = standardRate;
    }

    public double getFinalRate() {
        return finalRate;
    }

    public void setFinalRate(double finalRate) {
        this.finalRate = finalRate;
    }

    public double getProfitInWantedCurrency() {
        return profitInWantedCurrency;
    }

    public void setProfitInWantedCurrency(double profitInWantedCurrency) {
        this.profitInWantedCurrency = profitInWantedCurrency;
    }

    public double getProfitInSGD() {
        return profitInSGD;
    }

    public void setProfitInSGD(double profitInSGD) {
        this.profitInSGD = profitInSGD;
    }

}
