package com.oreshkin_aa.calc;

import com.oreshkin_aa.calc.tokens.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Queue<Token> queue = new LinkedList<>();
        queue.add(new TokenNumber(2));
        queue.add(new TokenPlus());
        queue.add(new TokenNumber(3));
        queue.add(new TokenPower());
        queue.add(new TokenNumber(4));

        ArrayList<Token> list;
        SortStation sortStation = new SortStation(queue);
        try {
            list = sortStation.getReversePolish();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ;
        }

        for (var t : list)
            System.out.println(t);

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
