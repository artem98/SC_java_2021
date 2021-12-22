package com.oreshkin_aa.fifteen_game;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
	// write your code here
        BoardState state = new BoardState(4);
        System.out.println(state);
        System.out.println("\nShuffled:");

        state.randomShuffle(150, 87);
        System.out.println(state);

        FifteenGameSolver solver = new FifteenGameSolver(state, 1, 1);
        solver.solve(false);

        solver = new FifteenGameSolver(state, 2, 3);
        solver.solve(false);

        solver = new FifteenGameSolver(state, 3, 2);
        solver.solve(false);
    }
}
