package com.oreshkin_aa;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Integer[] a = {1, 2, 1, 0, -2, 2, 1, 4, 3};
        Integer[] b = {0, 1, 1, 3, 3, 3, 6, 8};

        QuickSorter.qSort(a, Integer::compareTo);
        printArr(a);

        QuickSorter.split(a, 1, Integer::compareTo, 0, a.length);
        printArr(a);
    }

    public static <T> void printArr(T[] arr) {
        System.out.print("[");
        for(int i = 0; i < arr.length - 1; i++)
            System.out.print(arr[i] + ", ");
        System.out.println(arr[arr.length - 1] + "]");
    }
}

