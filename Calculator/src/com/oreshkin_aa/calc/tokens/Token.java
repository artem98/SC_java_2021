package com.oreshkin_aa.calc.tokens;

import java.util.Stack;

public abstract class Token {

    public abstract boolean shouldSendToStation();

    public Token() {
        setPriority();
    }

    protected abstract void setPriority();

    protected Priority priority;
    public Priority getPriority() {
        return priority;
    }

    public boolean canStackOver(Token token) {
        return priority.canStackOver(token.getPriority());
    }

    public abstract void applyToStack(Stack<Double> stack);
}
