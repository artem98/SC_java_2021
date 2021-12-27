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

        int randomStepsNumOverClosed = Math.abs(random.nextInt()) % (N * N - openedCount);

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

    public void openRandomBlock() {
        openBlock(getRandomClosedBlock());
    }

    public void openBlock(int i, int j) {
        openBlock(new Position(i, j));
    }

    public void openBlock(Position pos) {
        if(pos == null)
            return;

        if(isOpen[pos.i][pos.j] == 0)
            openedCount++;
        isOpen[pos.i][pos.j] = 1;

        int ownComponent = getComponentInd(pos.i, pos.j);
        var nbrs = pos.nbrs();
        for(Position nbr : nbrs) {
            if(!isOpen(nbr))
                continue;
            int nbrComponent = getComponentInd(nbr.i, nbr.j);
            connections.union(ownComponent, nbrComponent);
        }
    }

    public boolean isOpen(Position position) {
        if (position.i == TOP_BLOCK_INDEX
                || position.j == TOP_BLOCK_INDEX
                || position.i == BTM_BLOCK_INDEX
                || position.j == BTM_BLOCK_INDEX)
            return true;

        return isOpen[position.i][position.j] == 1;
    }

    public void clear() {
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                isOpen[i][j] = 0;
            }
        connections.clear();
        for(int j = 0; j < N; j++) {
            int currBlockComp = getComponentInd(0, j);
            int topBlockComp = getComponentInd(TOP_BLOCK_INDEX, TOP_BLOCK_INDEX);
            connections.union(currBlockComp, topBlockComp);
        }
        for(int j = 0; j < N; j++) {
            int currBlockComp = getComponentInd(N - 1, j);
            int btmBlockComp = getComponentInd(BTM_BLOCK_INDEX, BTM_BLOCK_INDEX);
            connections.union(currBlockComp, btmBlockComp);
        }
        int topComponent = getComponentInd(TOP_BLOCK_INDEX, TOP_BLOCK_INDEX);
        int btmComponent = getComponentInd(BTM_BLOCK_INDEX, BTM_BLOCK_INDEX);

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

    public boolean checkPercolation() {
        int topComponent = getComponentInd(TOP_BLOCK_INDEX, TOP_BLOCK_INDEX);
        int btmComponent = getComponentInd(BTM_BLOCK_INDEX, BTM_BLOCK_INDEX);

        return topComponent == btmComponent;
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
