package com.oreshkin_aa.calc.tokens;

import java.util.EmptyStackException;
import java.util.Stack;

public abstract class TokenUnary extends Token {

    public abstract double apply(double val);

    public void applyToStack(Stack<Double> stack) throws EmptyStackException {
        double val = stack.pop();

        stack.push(apply(val));
    }
}
