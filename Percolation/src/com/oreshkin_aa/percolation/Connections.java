package com.oreshkin_aa.percolation;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Connections {

    public int N;
    public int[] components;

    public Connections(int N) {
        this.N = N;
        components = new int[N];
    }

    public void clear() {
        for(int i = 0; i < N; i++)
            components[i] = i;
    }

    public int getComponent(int ind) {
        return components[ind];
    }
}
