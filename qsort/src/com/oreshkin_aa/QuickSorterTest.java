package com.oreshkin_aa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuickSorterTest {

    private ArrayList<Integer[]> mIntSamples;
    private ArrayList<Double[]> mDoubleSamples;

    private Double[] createRandomDoubleArray(Random random) {
        int len = Math.abs(random.nextInt()) % 1000 + 1;
        Double[] array = new Double[len];

        for(int i = 0; i < len; i++)
            array[i] = random.nextDouble();
        return array;
    }

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

        mDoubleSamples = new ArrayList<>();
        Random random = new Random(0);
        for(int i = 0; i < 50; i++) {
            mDoubleSamples.add(createRandomDoubleArray(random));
        }
    }

    @org.junit.jupiter.api.Test
    void sort() {
        prepareSamples();

        for (int id = 0; id < mIntSamples.size(); id++) {
            Integer[] actual = Arrays.copyOf(mIntSamples.get(id), mIntSamples.get(id).length);
            Integer[] expected = Arrays.copyOf(mIntSamples.get(id),mIntSamples.get(id).length);

            QuickSorter.sort(actual, Integer::compareTo);
            Arrays.sort(expected, Integer::compareTo);

            assertArrayEquals(expected, actual);
        }

        for (int id = 0; id < mDoubleSamples.size(); id++) {
            Double[] actual = Arrays.copyOf(mDoubleSamples.get(id), mDoubleSamples.get(id).length);
            Double[] expected = Arrays.copyOf(mDoubleSamples.get(id), mDoubleSamples.get(id).length);

            QuickSorter.sort(actual, Double::compareTo);
            Arrays.sort(expected, Double::compareTo);

            assertArrayEquals(expected, actual);
        }
    }

    @org.junit.jupiter.api.Test
    void qsort() {
        prepareSamples();

        for (int id = 0; id < mIntSamples.size(); id++) {
            System.out.println(id);
            Integer[] actual = Arrays.copyOf(mIntSamples.get(id), mIntSamples.get(id).length);
            Integer[] expected = Arrays.copyOf(mIntSamples.get(id), mIntSamples.get(id).length);

            QuickSorter.qSort(actual, Integer::compareTo);
            Arrays.sort(expected, Integer::compareTo);

            Main.printArr(mIntSamples.get(id));
            assertArrayEquals(expected, actual);
        }

        for (int id = 0; id < mDoubleSamples.size(); id++) {
            Double[] actual = Arrays.copyOf(mDoubleSamples.get(id), mDoubleSamples.get(id).length);
            Double[] expected = Arrays.copyOf(mDoubleSamples.get(id), mDoubleSamples.get(id).length);

            QuickSorter.qSort(actual, Double::compareTo);
            Arrays.sort(expected, Double::compareTo);

            assertArrayEquals(expected, actual);
        }
    }

    @org.junit.jupiter.api.Test
    void insertionSort() {

        prepareSamples();

        for (int id = 0; id < mIntSamples.size(); id++) {
            Integer[] actual = Arrays.copyOf(mIntSamples.get(id), mIntSamples.get(id).length);
            Integer[] expected = Arrays.copyOf(mIntSamples.get(id), mIntSamples.get(id).length);

            QuickSorter.insertionSort(actual, Integer::compareTo);
            Arrays.sort(expected, Integer::compareTo);

            assertArrayEquals(expected, actual);
        }
    }

}