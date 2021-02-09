package basic;

import util.Util;

/**
 * In a sorted array, find the left most index where its value is greater than or equal to the given value
 */
public class Code005BinarySearchLeftMostIndex {
    public static void main(String[] args) {
        final int testSum = 1_000_000;
        int[] arr;

        int size = 100, range = 30;
        int value, left1, left2;
        long start = Util.getSysTime();
        System.out.println("test start ...");
        for (int i = 0; i < testSum; i++) {
            arr = Util.generateRandomArray(size, range);
            value = Util.generateRandomValue(range);
            if (arr == null) {
                throw new RuntimeException("invalid array not allowed");
            }
            mergeSort(arr);
            left1 = searchLeftMostIndex(arr, value);
            left2 = binarySearchLeftMostIndex(arr, value);

            if (left1 != left2) {
                System.out.println("test fail ...");
                Util.printArray(arr);
                System.out.println("value: " + value + ", left1: " + left1 + ", left2: " + left2);
                return;
            }
        }
        long end = Util.getSysTime();
        System.out.println("test pass ... time taken : " + (end - start) / 1000f);
    }

    private static int binarySearchLeftMostIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int mid;
        int leftMost = -1;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] >= value) {
                leftMost = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return leftMost;
    }

    private static int searchLeftMostIndex(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int len = arr.length;
        int i = 0;
        while (i < len && arr[i] < value) {
            ++i;
        }
        return i == len ? -1 : i;
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
