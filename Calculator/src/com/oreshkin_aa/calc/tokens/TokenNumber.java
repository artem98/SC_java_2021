package com.oreshkin_aa.calc.tokens;

public class TokenNumber extends Token {

    public TokenNumber(double value) {
        super();

        this.value = value;
    }

    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean shouldSendToStation() {
        return false;
    }

    @Override
    protected void setPriority() {
        priority = new Priority();
    }
}
