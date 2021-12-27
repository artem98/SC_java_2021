package com.oreshkin_aa.percolation;

public class Main {

    public static void main(String[] args) {
        int n = 10;
        Grid grid = new Grid(n, 3);

        System.out.println(grid.randomSingleTest());
        System.out.println(grid.randomSingleTest());
        System.out.println(grid.randomSingleTest());
    }
}
