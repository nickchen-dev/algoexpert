package util;

public class Util {
    public static int[] generateRandomArray(int size, int range) {
        if (size < 0) {
            return null;
        }
        int[] arr = new int[(int) (Math.random() * size + 1)];
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * range + 1) - (int) (Math.random() * range);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + (i != len - 1 ? " " : "\n"));
        }
        System.out.println();
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        if (arr.length == 0) {
            return new int[0];
        }
        int len = arr.length;
        int[] copy = new int[len];
        System.arraycopy(arr, 0, copy, 0, len);
        return copy;
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            arr[i] ^= arr[j];
            arr[j] ^= arr[i];
            arr[i] ^= arr[j];
        }
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        // arr1 and arr2 are referring to same array OR they are both null
        if (arr1 == arr2) {
            return true;
        }
        // if one array is null and the other array is not null
        if (arr1 == null ^ arr2 == null) {
            return false;
        }
        int len = arr1.length;
        if (len != arr2.length) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static long getSysTime() {
        return System.currentTimeMillis();
    }

    public static int generateRandomValue(int range) {
        return (int) (Math.random() * range + 1) - (int) (Math.random() * range);
    }
}
