package com.oreshkin_aa.calc;

import com.oreshkin_aa.calc.tokens.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here

        ArrayList<Token> list = new ArrayList<>();
        list.add(new TokenNumber(2));
        list.add(new TokenNumber(3));
        list.add(new TokenUnaryMinus());

        StackCalculator calculator = new StackCalculator(list);
        double v = 0;
        try {
            v = calculator.calculate();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        System.out.println("Result " + v);
    }
}
