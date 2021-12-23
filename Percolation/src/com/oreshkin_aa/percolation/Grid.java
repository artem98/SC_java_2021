package com.oreshkin_aa.percolation;

import java.util.ArrayList;
import java.util.Random;

public class Grid {

    public int N;
    public char[][] isOpen;
    public int openedCount = 0;

    public Random random;

    /**
     * Union-find structure for blocks
     */
    private Connections connections;

    /**
     * Component index in union find for blocks
     */
    private int[][] componentInd;

    /**
     * Component index in union find for virtual top block
     */
    private int topComponentInd;
    private final static int TOP_BLOCK_INDEX = -1;

    /**
     * Component index in union find for virtual bottom block
     */
    private int btmComponentInd;
    private final static int BTM_BLOCK_INDEX = -2;

    public Grid(int n, int seed) {
        N = n;
        isOpen = new char[n][n];
        random = new Random(seed);
        connections = new Connections();
        componentInd = new int[n][n];

        this.clear();
    }

    private Position getRandomClosedBlock() {
        if(openedCount == N * N)
            return null;

        int randomStepsNumOverClosed = Math.abs(random.nextInt()) % (N - openedCount);

        int closedIter = 0;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                if(isOpen[i][j] == 1)
                    continue;

                if(closedIter == randomStepsNumOverClosed)
                    return new Position(i, j);
                closedIter++;
            }

        return null;
    }

    public void openBlock(int i, int j) {
        isOpen[i][j] = 1;
    }

    public void openBlock(Position pos) {
        isOpen[pos.i][pos.j] = 1;
    }

    public void clear() {
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                isOpen[i][j] = 0;
                componentInd[i][j] = i * N + j + 1;
            }
        connections.clear();
        openedCount = 0;
        this.topComponentInd = 0;
        this.btmComponentInd = N * N + 1;
    }


    private int getComponentInd(int i, int j) {
        if(i == TOP_BLOCK_INDEX || j == TOP_BLOCK_INDEX)
            return topComponentInd;

        if(i == BTM_BLOCK_INDEX || j == BTM_BLOCK_INDEX)
            return btmComponentInd;

        return componentInd[i][j];
    }

    private class Position {
        public int i;
        public int j;

        public Position(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public ArrayList<Position> nbrs() {

            ArrayList<Position> result = new ArrayList<>();

            if(i == 0)
                result.add(new Position(TOP_BLOCK_INDEX, TOP_BLOCK_INDEX));
            if(i == N - 1)
                result.add(new Position(BTM_BLOCK_INDEX, BTM_BLOCK_INDEX));

            if(i < N - 1)
                result.add(new Position(i + 1, j));
            if(j < N - 1)
                result.add(new Position(i, j + 1));
            if(i > 0)
                result.add(new Position(i - 1, j));
            if(j > 0)
                result.add(new Position(i, j - 1));

            return result;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        final int printLimit = 15;

        for(int i = 0; i < N; i++) {
            if(N > printLimit && i > printLimit && i < N - 5) {
                if(i == printLimit + 1)
                    builder.append(".............\n");
                continue;
            }
            for (int j = 0; j < N; j++) {
                if(N > printLimit && j > printLimit && j < N - 5) {
                    if(j == printLimit + 1)
                        builder.append(".... ");
                    continue;
                }
                if(isOpen[i][j] == 0)
                    builder.append("■ ");
                else
                    builder.append("□ ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
