package com.oreshkin_aa.fifteen_game;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BoardState state = new BoardState(3);
        System.out.println(state);
        System.out.println("\nnbrs:");

        state.randomShuffle(100, 55);
        System.out.println(state);
    }
}
