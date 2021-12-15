package com.oreshkin_aa.calc;

import com.oreshkin_aa.calc.tokens.Token;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class StackCalculator {

    private List<Token> tokenList;
    private Stack<Double> stack;

    public StackCalculator(List<Token> tokenList) {
        this.tokenList = tokenList;
        stack = new Stack<>();
    }

    public double calculate() throws Exception {
        for(Token token : tokenList) {
            try {
                token.applyToStack(stack);
            }
            catch (EmptyStackException e) {
                throw new Exception("Wrong expression! Cannot apply " + token);
            }
        }
        double result;
        try {
            result = stack.pop();
        }
        catch (EmptyStackException e) {
            throw new Exception("Wrong expression!");
        }
        return result;
    }
}
