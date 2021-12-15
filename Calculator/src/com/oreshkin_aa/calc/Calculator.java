package com.oreshkin_aa.calc;

import com.oreshkin_aa.calc.tokens.Token;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Calculator {

    public Calculator() {
        parser = new ExpressionParser();
    }

    private ExpressionParser parser;

    public double calculate(String input) throws Exception {
        LinkedList<Token> queue;
        try {
            queue = parser.parse(input);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw e;
        }

        ArrayList<Token> list;
        SortStation sortStation = new SortStation(queue);
        try {
            list = sortStation.getReversePolish();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw e;
        }

        StackCalculator calculator = new StackCalculator(list);
        double v = 0;
        try {
            v = calculator.calculate();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw e;
        }

        return v;
    }

}
