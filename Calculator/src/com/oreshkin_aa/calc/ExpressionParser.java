package com.oreshkin_aa.calc;

import com.oreshkin_aa.calc.tokens.*;

import java.util.LinkedList;
import java.util.List;

public class ExpressionParser {

    private static final String NUMERIC_CHARS = "1234567890.";

    private LinkedList<Token> output;

    public ExpressionParser() {
        this.output = new LinkedList<>();
    }

    public LinkedList<Token> parse(String string) throws Exception {
        output.clear();

        StringBuilder numberString = new StringBuilder("");
        char prev = '(';

        for(int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            processCharacter(numberString, c, prev);
            prev = c;
        }
        if(!numberString.isEmpty()) {
            String doubleString = numberString.toString();
            double val = Double.parseDouble(doubleString);
            output.add(new TokenNumber(val));
            numberString.setLength(0);
        }

        return output;
    }

    private void processCharacter(StringBuilder numberString, char c, char prev) throws Exception {
        if(isNumChar(c)) {
            numberString.append(c);
            return ;
        }

        // End of number
        if(!numberString.isEmpty()) {
            String doubleString = numberString.toString();
            double val = Double.parseDouble(doubleString);
            output.add(new TokenNumber(val));
            numberString.setLength(0);
        }

        if(c == '(') {
            output.add(new TokenBracket(true));
            return;
        }

        if(c == ')') {
            output.add(new TokenBracket(false));
            return;
        }

        if(c == '+') {
            output.add(new TokenPlus());
            return;
        }

        if(c == '-') {
            if (prev == '(')
                output.add(new TokenUnaryMinus());
            else
                output.add(new TokenMinus());
            return;
        }

        if(c == '*') {
            output.add(new TokenMult());
            return;
        }

        if(c == '/') {
            output.add(new TokenDiv());
            return;
        }

        if(c == '^') {
            output.add(new TokenPower());
            return;
        }

        if(c == ' ')
            return;

        throw new Exception("Wrong symbol in expression!");
    }

    private static boolean isNumChar(char c) {
        for(int i = 0; i < NUMERIC_CHARS.length(); i++) {
            if(c == NUMERIC_CHARS.charAt(i))
                return true;
        }
        return false;
    }

}
