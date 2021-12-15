package com.oreshkin_aa.calc;

import com.oreshkin_aa.calc.tokens.Token;
import com.oreshkin_aa.calc.tokens.TokenBracket;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class SortStation {

    private Queue<Token> inputList;
    private Stack<Token> station;
    private ArrayList<Token> outputList;

    public SortStation(Queue<Token> inputList) {
        this.inputList = inputList;
        this.outputList = new ArrayList<>();
        station = new Stack<>();
    }

    public ArrayList<Token> getReversePolish() throws Exception {

        while(!inputList.isEmpty()) {
            Token token = inputList.poll();
            processToken(token);
        }

        while(!station.isEmpty()) {
            Token popped = station.pop();
            outputList.add(popped);
        }

        return outputList;
    }

    private void processToken(Token token) throws Exception {

        if(!token.shouldSendToStation()) {
            outputList.add(token);
            return ;
        }

        if(token instanceof TokenBracket && ((TokenBracket) token).isClosing()) {
            processClosingBracket();
            return;
        }

        if(station.isEmpty()) {
            station.push(token);
            return;
        }

        processWithPriority(token);
    }

    private void processWithPriority(Token token) {
        while(!station.isEmpty()) {
            Token stationTop = station.peek();

            if(token.canStackOver(stationTop))
                break;

            stationTop = station.pop();
            outputList.add(stationTop);
        }
        station.push(token);
    }

    private void processClosingBracket() throws Exception {
        while (!station.isEmpty()) {
            Token stationTop = station.pop();

            if(stationTop instanceof TokenBracket && ((TokenBracket) stationTop).isOpening())
                return ;

            outputList.add(stationTop);
        }
        throw new Exception("Wrong brackets configuration");
    }
}
