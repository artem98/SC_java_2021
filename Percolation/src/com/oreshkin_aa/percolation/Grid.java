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
    private final Connections connections;

    private final static int TOP_BLOCK_INDEX = -1;
    private final static int BTM_BLOCK_INDEX = -2;

    public Grid(int n, int seed) {
        N = n;
        isOpen = new char[n][n];
        random = new Random(seed);
        connections = new Connections(n * n + 2);

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
        openBlock(pos.i, pos.j);
    }

    public void clear() {
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                isOpen[i][j] = 0;
            }
        connections.clear();
        openedCount = 0;
    }

    private int getLinearIndex(Position pos) {
        return getLinearIndex(pos.i, pos.j);
    }

    private int getLinearIndex(int i, int j) {
        if(i == TOP_BLOCK_INDEX || j == TOP_BLOCK_INDEX)
            return 0;

        if(i == BTM_BLOCK_INDEX || j == BTM_BLOCK_INDEX)
            return N * N + 1;

        return i * N + j + 1;
    }

    private int getComponentInd(int i, int j) {
        int linearInd = getLinearIndex(i, j);
        return connections.getComponent(linearInd);
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
