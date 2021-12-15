package com.oreshkin_aa.calc.tokens;

import java.util.EmptyStackException;
import java.util.Stack;

public class TokenMult extends TokenBinary {

    public TokenMult() {
        super();
    }

    @Override
    public boolean shouldSendToStation() {
        return true;
    }

    @Override
    protected void setPriority() {
        priority = new Priority(true, false, 2);
    }

    @Override
    public double apply(double val1, double val2) {
        return val1 * val2;
    }
}
