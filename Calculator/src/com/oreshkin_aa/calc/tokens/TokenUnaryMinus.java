package com.oreshkin_aa.calc.tokens;

public class TokenUnaryMinus extends TokenUnary {

    public TokenUnaryMinus() {
        super();
    }

    @Override
    public boolean shouldSendToStation() {
        return true;
    }

    @Override
    protected void setPriority() {
        priority = new Priority(false, false, 3);
    }

    @Override
    public double apply(double val) {
        return -val;
    }
}
