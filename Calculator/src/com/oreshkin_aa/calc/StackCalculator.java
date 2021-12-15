package com.oreshkin_aa.calc;

import com.oreshkin_aa.calc.tokens.Token;

import java.util.Stack;

public class StackCalculator {

    private Stack<Token> tokenStack;
    private Stack<Double> stack;

    public StackCalculator(Stack<Token> tokenStack) {
        this.tokenStack = tokenStack;
    }

    public double calculate() {

    }
}
