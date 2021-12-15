package com.oreshkin_aa.calc.tokens;

import java.util.Stack;

public class TokenBracket extends Token {

    public TokenBracket(boolean isOpening) {
        super();

        this.isOpening = isOpening;
    }

    private boolean isOpening;

    public boolean isOpening() {
        return isOpening;
    }

    public void setOpening(boolean opening) {
        isOpening = opening;
    }

    public boolean isClosing() {
        return !isOpening;
    }

    @Override
    public boolean shouldSendToStation() {
        return true;
    }

    @Override
    protected void setPriority() {
        priority = new Priority(false, true, 0);
    }

    @Override
    public void applyToStack(Stack<Double> stack) {
    }
}
