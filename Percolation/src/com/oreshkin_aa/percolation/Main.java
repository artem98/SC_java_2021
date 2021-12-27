package com.oreshkin_aa.percolation;

public class Main {

    public static void main(String[] args) {
        int n = 8;
        Grid grid = new Grid(n, 3);
        int i = 0;
        while(!grid.checkPercolation() && i < n * n) {
            System.out.println(i);
            grid.openRandomBlock();
            i++;
        }
        System.out.println(grid);
        System.out.println(grid.checkPercolation());
    }
}
