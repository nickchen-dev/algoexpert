package basic;

import util.Util;

/**
 * In a sorted array, find the right most index where its value is less than or equal to the given value
 */
public class Code006BinarySearchRightMostIndex {
    public static void main(String[] args) {
        final int testSum = 1_000_000;
        int[] arr;

        int size = 100, range = 30;
        int value, right1, right2;
        long start = Util.getSysTime();
        System.out.println("test start ...");
        for (int i = 0; i < testSum; i++) {
            arr = Util.generateRandomArray(size, range);
            value = Util.generateRandomValue(range);
            if (arr == null) {
                throw new RuntimeException("invalid array not allowed");
            }
            mergeSort(arr);
            right1 = searchRightMostIndex(arr, value);
            right2 = binarySearchRightMostIndex(arr, value);

            if (right1 != right2) {
                System.out.println("test fail ...");
                Util.printArray(arr);
                System.out.println("value: " + value + ", right1: " + right1 + ", right2: " + right2);
                return;
            }
        }
        long end = Util.getSysTime();
        System.out.println("test pass ... time taken : " + (end - start) / 1000f);
    }

    private static int binarySearchRightMostIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int mid;
        int rightMost = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] <= value) {
                rightMost = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return rightMost;
    }

    private static int searchRightMostIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int len = arr.length;
        int i = 0;
        while (i < len && arr[i] <= value) {
            ++i;
        }
        return i - 1;
    }

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int left = 0;
        int right = arr.length - 1;
        arrSort(arr, left, right);
    }

    private static void arrSort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        arrSort(arr, left, mid);
        arrSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= right) {
            help[i++] = arr[p2++];
        }
        int len = help.length;
        for (i = 0; i < len; i++) {
            arr[left + i] = help[i];
        }
    }
}
