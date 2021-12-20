package com.oreshkin_aa.fifteen_game;

import java.util.*;

public class BoardState {

    public int size;
    public int[][] cellToNum;
    public int N;

    private Position emptyCellPosition;

    public BoardState(int size) {
        this.size = size;
        N = size * size;
        cellToNum = new int[size][size];
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                cellToNum[i][j] = i * size + j + 1;

        emptyCellPosition = new Position(size - 1, size - 1);
    }

    /**
     * Shuffles board using legal steps
     * @param stepNumber - number of single random moves on the board
     */
    public void randomShuffle(int stepNumber, int seed) {
        Random random = new Random(seed);
        for(int i = 0; i < stepNumber; i++) {
            ArrayList<BoardState> nbrs = neighbours();
            int newStateInd = Math.abs(random.nextInt()) % (nbrs.size());
            this.copyFrom(nbrs.get(newStateInd));
            System.out.println(this);
        }
    }

    public int manhattanDist(BoardState rhs) {
        if(rhs.size != size)
            return Integer.MAX_VALUE;

        int result = 0;
        //Here we sacrifice speed because we don't want to double memory size to store numberToCell array
        for(int value = 0; value < N; value++) {
            int ownI = 0;
            int ownJ = 0;
            int rhsI = 0;
            int rhsJ = 0;
            for(int i = 0; i < size; i++)
                for(int j = 0; j < size; j++) {
                    if(cellToNum[i][j] == value) {
                        ownI = i;
                        ownJ = j;
                    }
                    if(rhs.cellToNum[i][j] == value) {
                        rhsI = i;
                        rhsJ = j;
                    }
                }
            result += Math.abs(ownI - rhsI) + Math.abs(ownJ - rhsJ);
        }
        return result;
    }



    public ArrayList<BoardState> neighbours() {
        ArrayList<Position> nbrsOfEmptyCell = emptyCellPosition.neighbours();
        ArrayList<BoardState> result = new ArrayList<>(nbrsOfEmptyCell.size());

        for(Position emptyNbr : nbrsOfEmptyCell) {
            BoardState newState = this.copy();
            newState.swap(emptyNbr);
            result.add(newState);
        }

        return result;
    }

    public void swap(Position pos1) {
        Position pos2 = emptyCellPosition;
        int v1 = cellToNum[pos1.i][pos1.j];
        int v2 = cellToNum[pos2.i][pos2.j];

        cellToNum[pos1.i][pos1.j] = v2;
        cellToNum[pos2.i][pos2.j] = v1;
        emptyCellPosition.i = pos1.i;
        emptyCellPosition.j = pos1.j;
    }

    public void copyFrom(BoardState rhs) {
        this.size = rhs.size;
        this.N = rhs.N;
        this.emptyCellPosition.i = rhs.emptyCellPosition.i;
        this.emptyCellPosition.j = rhs.emptyCellPosition.j;

        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                this.cellToNum[i][j] = rhs.cellToNum[i][j];
    }

    /**
     *
     * @return new BoardState, full copy of this
     */
    public BoardState copy() {
        BoardState result = new BoardState(this.size);
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                result.cellToNum[i][j] = cellToNum[i][j];

        result.emptyCellPosition.i = emptyCellPosition.i;
        result.emptyCellPosition.j = emptyCellPosition.j;
        return result;
    }

    private class Position {
        public int i;
        public int j;

        public Position(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public boolean isCorrect(int i, int j) {
            return (i >= 0) && (j >= 0) && (i < size) && (j < size);
        }

        public ArrayList<Position> neighbours() {
            ArrayList<Position> list = new ArrayList<>();

            if(isCorrect(i + 1, j))
                list.add(new Position(i + 1, j));
            if(isCorrect(i - 1, j))
                list.add(new Position(i - 1, j));
            if(isCorrect(i, j + 1))
                list.add(new Position(i, j + 1));
            if(isCorrect(i, j - 1))
                list.add(new Position(i, j - 1));
            return list;
        }

        @Override
        public String toString() {
            return "Pos(" +
                    i + ", " +
                    j +
                    ')';
        }
    }

    public boolean isInitState() {
        BoardState initState = new BoardState(this.size);
        return this.equals(initState);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String maxString = Integer.toString(N);
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cellToNum[i][j] == N) {
                    builder.append(String.format(" %" + maxString.length() + "d", 1).replace('1', '*'));
                    continue;
                }
                String formatted = String.format(" %" + maxString.length() + "d", cellToNum[i][j]);
                builder.append(formatted);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardState state = (BoardState) o;
        boolean res = size == state.size;
        for(int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                res = res && (cellToNum[i][j] == state.cellToNum[i][j]);
        return res;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
            for(int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    result = 31 * result + cellToNum[i][j];
        return result;
    }
}
