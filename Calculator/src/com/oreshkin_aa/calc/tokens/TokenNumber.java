package com.oreshkin_aa.calc.tokens;

public class TokenNumber extends Token {

    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
