package com.oreshkin_aa.calc;

import com.oreshkin_aa.calc.tokens.*;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("Enter expression (\"q\" - exit):");
            String input = reader.readLine();

            if(input.length() == 0)
                continue;

            if(input.length() == 1 && input.charAt(0) == 'q')
                break;

            ExpressionParser parser = new ExpressionParser();
            Queue<Token> queue;
            try {
                queue = parser.parse(input);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                continue;
            }

            ArrayList<Token> list;
            SortStation sortStation = new SortStation(queue);
            try {
                list = sortStation.getReversePolish();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                continue;
            }

            StackCalculator calculator = new StackCalculator(list);
            double v = 0;
            try {
                v = calculator.calculate();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println("Result: " + v);
        }
    }
}
