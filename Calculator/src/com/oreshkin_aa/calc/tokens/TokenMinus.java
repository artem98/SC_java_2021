package com.oreshkin_aa.calc.tokens;

public class TokenMinus extends TokenBinary {

    public TokenMinus() {
        super();
    }

    @Override
    public boolean shouldSendToStation() {
        return true;
    }

    @Override
    protected void setPriority() {
        priority = new Priority(true, false, 1);
    }

    @Override
    public double apply(double val1, double val2) {
        return val1 - val2;
    }
}
