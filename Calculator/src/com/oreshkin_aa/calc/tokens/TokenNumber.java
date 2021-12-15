package com.oreshkin_aa.calc.tokens;

import java.util.Stack;

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

    @Override
    public void applyToStack(Stack<Double> stack) {
        stack.push(value);
    }

    @Override
    public String toString() {
        return "TokenNumber{" +
                "value=" + value +
                '}';
    }
}
