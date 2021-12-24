package com.oreshkin_aa.percolation;

public class Connections {

    public int N;
    public int[] parent;
    public int[] rank;

    public Connections(int N) {
        this.N = N;
        parent = new int[N];
        rank = new int[N];
        clear();
    }

    public int find(int x) {
        if(parent[x] == x)
            return x;

        int findRes = find(x);
        parent[x] = findRes;
        return findRes;
    }

    public void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y)
            return;

        if(rank[x] < rank[y])
            parent[x] = y;
        else {
            parent[y] = x;
            if(rank[x] == rank[y])
                rank[x]++;
        }
    }

    public void clear() {
        for(int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int getComponent(int ind) {
        return parent[ind];
    }
}
