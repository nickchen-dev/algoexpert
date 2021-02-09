package basic;

import util.Util;

import java.util.Arrays;

/**
 * Bubble Sort
 */
public class Code002BubbleSort {
    public static void main(String[] args) {
        final int testSum = 1_000_000;
        int[] arr, arr1, arr2;

        int size = 100, range = 500;
        long start = Util.getSysTime();
        System.out.println("test start ...");
        for (int i = 0; i < testSum; i++) {
            arr = Util.generateRandomArray(size, range);
            if (arr == null) {
                throw new RuntimeException("invalid array not allowed");
            }
            arr1 = Util.copyArray(arr);
            arr2 = Util.copyArray(arr);

            Arrays.sort(arr1);
            bubbleSort(arr2);

            if (!Util.isEqual(arr1, arr2)) {
                System.out.println("test fail ...");
                Util.printArray(arr);
                Util.printArray(arr1);
                Util.printArray(arr2);
                return;
            }
        }
        long end = Util.getSysTime();
        System.out.println("test pass ... time taken : " + (end - start) / 1000f);
    }

    private static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    Util.swap(arr, j + 1, j);
                }
            }
        }
    }
}
