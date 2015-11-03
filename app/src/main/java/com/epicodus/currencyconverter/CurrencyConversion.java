package com.epicodus.currencyconverter;

/**
 * Created by Guest on 11/3/15.
 */
public class CurrencyConversion {

    private String mSource;
    private String mTarget;
    private Double mRate;

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

    public Double getRate() {
        return mRate;
    }

    public void setRate(Double rate) {
        mRate = rate;
    }

    public String getStringRate() {
        return String.format("%.2f", mRate);
    }


    public String getStringResult(Double amount) {
        return String.format("%.2f", amount * mRate);
    }
}
