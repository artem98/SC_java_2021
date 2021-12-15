package com.oreshkin_aa.calc.tokens;

import java.util.EmptyStackException;
import java.util.Stack;

public abstract class TokenBinary extends Token{

    public abstract double apply(double val1, double val2);

    @Override
    public void applyToStack(Stack<Double> stack) throws EmptyStackException {
        double val2 = stack.pop();
        double val1 = stack.pop();

        stack.push(apply(val1, val2));
    }
}
