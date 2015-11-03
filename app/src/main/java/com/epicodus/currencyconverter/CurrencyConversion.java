package com.epicodus.currencyconverter;

/**
 * Created by Guest on 11/3/15.
 */
public class CurrencyConversion {

    private String mSource;
    private String mTarget;
    private double mRate;
    private double mAmount;

    public String getSource() {
        return mSource;
    }

    public void setSource(String source) {
        mSource = source;
    }

    public String getTarget() {
        return mTarget;
    }

    public void setTarget(String target) {
        mTarget = target;
    }

    public double getRate() {
        return mRate;
    }

    public void setRate(double rate) {
        mRate = rate;
    }

    public double getAmount() {
        return mAmount;
    }

    public void setAmount(double amount) {
        mAmount = amount;
    }
}
