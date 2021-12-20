package com.oreshkin_aa.fifteen_game;

import java.util.Arrays;
import java.util.Objects;

public class BoardState {

    public int size;
    public int[][] cellToNum;
    public int N;

    public BoardState(int size) {
        this.size = size;
        N = size * size;
        cellToNum = new int[size][size];
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                cellToNum[i][j] = i * size + j + 1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String maxString = Integer.toString(N);
        System.out.println(maxString);
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

    public boolean isInitState() {
        BoardState initState = new BoardState(this.size);
        return this.equals(initState);
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
