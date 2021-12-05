package com.oreshkin_aa;

public class Main {

    public static void main(String[] args) {
        QuickSorterTest.testComparesCount();
    }

    public static <T> void printArr(T[] arr) {
        if(arr.length == 0) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        for(int i = 0; i < arr.length - 1; i++)
            System.out.print(arr[i] + ", ");
        System.out.println(arr[arr.length - 1] + "]");
    }
}

