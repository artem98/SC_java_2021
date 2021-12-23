package com.oreshkin_aa.percolation;

public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(28, 0);
        grid.openBlock(2, 2);
        grid.openBlock(2, 3);
        System.out.println(grid);
    }
}
