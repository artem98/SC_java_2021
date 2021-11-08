package com.oreshkin_aa;

import java.util.Comparator;

public class QuickSorter {

    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {

        for (int end = 1; end < arr.length; end++) {
            int insertPos = binSearch(arr, 0, end, arr[end], comparator);
        }
    }

    public static <T> void qSort(T[] arr, Comparator<T> comparator) {

    }

    public static <T> int binSearch(T[] arr, int start, int end, T elem, Comparator<T> comparator) {
        if (end <= start)
            return -1;

        int pos = (start + end) / 2;
        while(end - start > 0) {
            if(comparator.compare(arr[pos], elem) < 0)
                start = pos + 1;
            else
                end = pos;
            pos = (start + end) / 2;
        }
        return pos;
    }
}
