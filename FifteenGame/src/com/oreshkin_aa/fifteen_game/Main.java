package com.oreshkin_aa.fifteen_game;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BoardState state = new BoardState(4);
        System.out.println(state);
        System.out.println("\nShuffled:");

        state.randomShuffle(20, 59);
        System.out.println(state);

        FifteenGameSolver solver = new FifteenGameSolver(state, 1, 1);
        solver.solve();
    }
}
