package com.oreshkin_aa.fifteen_game;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BoardState state = new BoardState(2);
        System.out.println(state);
        System.out.println(state.isInitState());
    }
}
