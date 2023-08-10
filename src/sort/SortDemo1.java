package sort;

import java.util.HashMap;

/**
 * @Author qinwenlong
 * @Date 2022/3/25
 **/
public class SortDemo1 {
    public static void main(String[] args) {
        HashMap<Integer,Integer> hashMap = new HashMap();
        hashMap.put(1,2);
        hashMap.put(2,3);
        hashMap.clear();
        long count = hashMap.values().stream().mapToInt(Integer::intValue).sum();
        int[] array = new int[]{3, 8, 2, 5, 1, 2};
        selectSort(array);
        System.out.println(array);
    }

    public static void insertSort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            if (array[i + 1] < array[i]) {
                swap(i, i + 1, array);
                for (int j = 0; j < i; j++) {
                    if (array[i] < array[j]) {
                        swap(i, j, array);
                    }
                }

            }
        }
    }

    public static void bubbleSort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1, array);
                }
            }
        }
    }

    public static void selectSort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (array[i] > array[j]) {
                    swap(i, j, array);
                }
            }
        }
    }

    public static void swap(int i, int j, int[] array) {
        if (i == j) {
            return;
        }
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
