package com.oreshkin_aa.calc.tokens;

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
}
