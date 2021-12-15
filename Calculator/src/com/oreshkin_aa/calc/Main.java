package com.oreshkin_aa.calc;

import com.oreshkin_aa.calc.tokens.*;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Calculator calculator = new Calculator();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("Enter expression (\"q\" - exit):");
            String input = reader.readLine();

            if(input.length() == 0)
                continue;

            if(input.length() == 1 && input.charAt(0) == 'q')
                break;

            double v;
            try {
                v = calculator.calculate(input);
            }
            catch (Exception e) {
                continue;
            }

            System.out.println("Result: " + v);
        }
    }
}
