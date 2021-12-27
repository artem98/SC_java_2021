package com.oreshkin_aa.percolation;

public class Main {

    public static void main(String[] args) {
        int n = 100;
        Grid grid = new Grid(n, 6);

        Statistic stat = grid.randomMultipleTest(300);
        System.out.println(stat);
    }
}
