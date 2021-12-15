package com.oreshkin_aa.calc.tokens;

public class TokenPower extends TokenBinary {

    public TokenPower() {
        super();
    }

    @Override
    public boolean shouldSendToStation() {
        return true;
    }

    @Override
    protected void setPriority() {
        priority = new Priority(false, true, 4);
    }

    @Override
    public double apply(double val1, double val2) {
        return Math.pow(val1, val2);
    }
}
