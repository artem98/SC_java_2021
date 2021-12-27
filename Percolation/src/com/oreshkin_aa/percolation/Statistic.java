package com.oreshkin_aa.percolation;

import java.lang.reflect.Array;
import java.util.List;

public class Statistic {

    public double mean = 0;
    public double dispersion = 0;

    public Statistic() {}

    public Statistic(List<Double> list) {
        initByList(list);
    }

    public void clear() {
        mean = dispersion = 0;
    }

    public void initByList(List<Double> list) {
        this.clear();

        for(Double v : list)
            mean += v;
        mean /= list.size();

        for(Double v : list)
            dispersion += (v - mean) * (v - mean);
        dispersion /= list.size();
    }
}
