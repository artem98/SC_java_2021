package com.oreshkin_aa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuickSorterTest {

    private ArrayList<Integer[]> mIntSamples;

    public void prepareSamples() {
        mIntSamples = new ArrayList<>();
        mIntSamples.add(new Integer[]{1, 2, 3, 4, 5, 6});
        mIntSamples.add(new Integer[]{-1, -2, -3, 4, 5, 6});
        mIntSamples.add(new Integer[]{-1, -1, 0, 0, 0, 2, 3, 4, 5, 6});
        mIntSamples.add(new Integer[]{2, 4, 8, 0, 2, -1, 3, 5, 3, 8});
        mIntSamples.add(new Integer[]{8, 7, 6, 5, 4, 3});
        mIntSamples.add(new Integer[]{8, 7, 6, 5, 5, 5});
        mIntSamples.add(new Integer[]{1, 1, 1, 1, 1, 1});
        mIntSamples.add(new Integer[]{1});
        mIntSamples.add(new Integer[]{});
        mIntSamples.add(new Integer[]{1, 2});
        mIntSamples.add(new Integer[]{2, 1});
        mIntSamples.add(new Integer[]{7, 7, 7, 8, 8, 8, 8});
        mIntSamples.add(new Integer[]{7, -1, 3, 0, 11, 1000, 3, -2, 4, 12, 14, 2, -2, 1000, 7});
        mIntSamples.add(new Integer[]{6, 9, 1, 5, -2, 9, 4, 9, 0, 3});
        mIntSamples.add(new Integer[]{6, 6, 6, 5, -2, 6, 6, 6, 6, 6});
    }

    @org.junit.jupiter.api.Test
    void qsort() {
        prepareSamples();

        for (int id = 0; id < mIntSamples.size(); id++) {
            Integer[] actual = Arrays.copyOf(getIntArray(id), getIntArray(id).length);
            Integer[] expected = Arrays.copyOf(getIntArray(id), getIntArray(id).length);

            QuickSorter.insertionSort(actual, Integer::compareTo);
            Arrays.sort(expected, Integer::compareTo);

            System.out.println(id);
            assertArrayEquals(expected, actual);
        }
    }

    @org.junit.jupiter.api.Test
    void insertionSort() {

        prepareSamples();

        for (int id = 0; id < mIntSamples.size(); id++) {
            Integer[] actual = Arrays.copyOf(getIntArray(id), getIntArray(id).length);
            Integer[] expected = Arrays.copyOf(getIntArray(id), getIntArray(id).length);

            QuickSorter.insertionSort(actual, Integer::compareTo);
            Arrays.sort(expected, Integer::compareTo);

            System.out.println(id);
            assertArrayEquals(expected, actual);
        }
    }

    Integer[] getIntArray(int id) {
        final int len = mIntSamples.size();
        id = id % len;
        return mIntSamples.get(id);
    }
}