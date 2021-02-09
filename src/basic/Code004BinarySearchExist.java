package basic;

import util.Util;

/**
 * In a sorted array, use binary search to check the existence of given value
 */
public class Code004BinarySearchExist {
    public static void main(String[] args) {
        final int testSum = 1_000_000;
        int[] arr, arr1;

        int size = 100, range = 30;
        int value;
        long start = Util.getSysTime();
        System.out.println("test start ...");
        for (int i = 0; i < testSum; i++) {
            arr = Util.generateRandomArray(size, range);
            value = Util.generateRandomValue(range);
            if (arr == null) {
                throw new RuntimeException("invalid array not allowed");
            }
            arr1 = Util.copyArray(arr);
            mergeSort(arr1);

            if (searchValue(arr, value) != binarySearchValue(arr1, value)) {
                System.out.println("test fail ...");
                Util.printArray(arr);
                Util.printArray(arr1);
                return;
            }
        }
        long end = Util.getSysTime();
        System.out.println("test pass ... time taken : " + (end - start) / 1000f);
    }

    private static boolean searchValue(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        for (int i : arr) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    private static boolean binarySearchValue(int[] arr, int value) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int mid;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] == value) {
                return true;
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return arr[left] == value;
    }

    private static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        arrSort(arr, 0, arr.length - 1);
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
