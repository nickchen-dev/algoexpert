package basic;

import util.Util;

/**
 * In a unsorted array, find an index where its value is both less than its left and right
 */
public class Code007BinarySearchLocalMinimum {
    public static void main(String[] args) {
        int[] arr;
        int size = 20, range = 80;

        arr = Util.generateRandomArray(size, range);
        int localMin = binarySearchLocalMin(arr);

        Util.printArray(arr);
        System.out.println("index: " + localMin + ", value: " + arr[localMin]);
    }

    private static int binarySearchLocalMin(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new RuntimeException("array is too short");
        }
        int left = 0;
        int right = arr.length - 1;
        if (arr[left] < arr[left + 1]) {
            return left;
        }
        if (arr[right] < arr[right - 1]) {
            return right;
        }
        left += 1;
        right -= 1;
        int mid;
        while (left <= right) {
            mid = left + ((right - left) >> 1);
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            } else if (arr[mid] >= arr[mid - 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }
}
