package com.oreshkin_aa.fifteen_game;

import java.util.*;

public class FifteenGameSolver {

    public BoardState state;

    private PriorityQueue<BoardStateInGraph> priorityQueue;
    private BoardState correctState;
    private HashSet<BoardStateInGraph> visited;

    public FifteenGameSolver(BoardState init, int stepsWeight, int distWeight) {
        state = init;
        correctState = new BoardState(init.size);

        priorityQueue = new PriorityQueue<>((o1, o2) -> {
            int manhDist1 = o1.state.manhattanDist(correctState);
            int manhDist2 = o2.state.manhattanDist(correctState);

            int stepsCount1 = o1.steps;
            int stepsCount2 = o2.steps;

            int metric1 = distWeight * manhDist1 + stepsWeight * stepsCount1;
            int metric2 = distWeight * manhDist2 + stepsWeight * stepsCount2;
            return metric1 - metric2;
        });
        priorityQueue.add(new BoardStateInGraph(init));

        visited = new HashSet<>();
        visited.add(new BoardStateInGraph(init));
    }

    public void solve() {

        BoardStateInGraph solution = null;
        BoardStateInGraph current;
        boolean doBreak = false;
        while (true) {
            current = priorityQueue.poll();
            visited.add(current);

            ArrayList<BoardState> nbrs = current.state.neighbours();
            for (BoardState nbr : nbrs) {

                BoardStateInGraph nbrInGraph = new BoardStateInGraph(nbr);
                nbrInGraph.prev = current;
                nbrInGraph.steps = current.steps + 1;

                if(visited.contains(nbrInGraph))
                    continue;

                if (nbr.equals(correctState)) {
                    solution = nbrInGraph;
                    doBreak = true;
                    break;
                }

                priorityQueue.add(nbrInGraph);
            }
            if (doBreak)
                break;
        }
        System.out.println("Solution found!");

        LinkedList<BoardStateInGraph> solutionSequence = new LinkedList<>();
        current = solution;
        for(current = solution; current != null; current = current.prev)
            solutionSequence.add(current);

        Collections.reverse(solutionSequence);

        for(var stateInGraph : solutionSequence) {
            System.out.println(stateInGraph.state);
        }
    }

    private static class BoardStateInGraph {

        public BoardState state;
        public BoardStateInGraph prev = null;
        public int steps = 0;

        public BoardStateInGraph(BoardState state) {
            this.state = state;
            prev = null;
            steps = 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BoardStateInGraph that = (BoardStateInGraph) o;
            return Objects.equals(state, that.state);
        }

        @Override
        public int hashCode() {
            return Objects.hash(state);
        }
    }
}
