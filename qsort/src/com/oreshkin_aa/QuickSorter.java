package com.oreshkin_aa;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSorter {

    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        for (int end = 1; end < arr.length; end++) {
            int insertPos = binSearch(arr, 0, end, arr[end], comparator);
            cycleShift(arr, insertPos, end);
        }
    }

    private static <T> void cycleShift(T[] arr, int start, int end) {
        T tmp;
        tmp = arr[end];
        for (int i = end; i > start; i--)
            arr[i] = arr[i - 1];
        arr[start] = tmp;
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

    public static <T> void qSort(T[] arr, Comparator<T> comparator) {
        qSort(arr, comparator, 0, arr.length);
    }

    private static <T> void qSort(T[] arr, Comparator<T> comparator, int start, int end) {
        int splitPos;
        T splitVal;

        int left = start;
        int right = end;
        int splittingBorder;

        while (left < right - 1) {
            splitPos = selectSplittingElem(arr, left, right);
            splitVal = arr[splitPos];
            splittingBorder = split(arr, splitVal, comparator, left, right);

            Main.printArr(arr);

            System.out.println("   " + left + " " + right);

            int len1 = splittingBorder - left;
            int len2 = right - splittingBorder;
            if(len1 < len2) {
                qSort(arr, comparator, left, splittingBorder);
                left = splittingBorder;
            }
            else {
                qSort(arr, comparator, splittingBorder, right);
                right = splittingBorder;
            }
        }
    }

    private static <T> int selectSplittingElem(T[] arr, int left, int right) {
        return (left + right) / 2;
    }

    public static <T> int split(T[] arr, T val, Comparator<T> comparator, int start, int end) {
        int left = start;
        int right = end - 1;

        T tmp;
        while (left < right) {
            for(;left < right && comparator.compare(arr[left], val) < 0; left++);
            for(;left < right && comparator.compare(arr[right], val) > 0; right--);
            if (left == right)
                return left;

            tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
        return left;
    }

}
