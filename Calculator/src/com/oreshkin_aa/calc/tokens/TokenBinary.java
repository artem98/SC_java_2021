package com.oreshkin_aa.calc.tokens;

public abstract class TokenBinary extends Token{

    public abstract double apply(double val1, double val2);
}
