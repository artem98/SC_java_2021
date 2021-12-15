package com.oreshkin_aa.calc.tokens;

public class TokenBracket extends Token {

    private boolean isOpening;
    private boolean isClosing;

    public boolean isOpening() {
        return isOpening;
    }

    public void setOpening(boolean opening) {
        isOpening = opening;
    }

    public boolean isClosing() {
        return isClosing;
    }

    public void setClosing(boolean closing) {
        isClosing = closing;
    }
}
