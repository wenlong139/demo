package leetcode;

import java.util.Stack;

/**
 * @Author qinwenlong
 * @Date 2021/12/2
 **/
public class MiddleNumSolution {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int index1 = 0;
        int index2 = 0;
        int cursor = 0;
        int[] array = new int[len1 + len2];
        while (index1 < len1 && index2 < len2) {
            if (nums1[index1] <= nums2[index2]) {
                array[cursor] = nums1[index1];
                index1++;
            } else {
                array[cursor] = nums2[index2];
                index2++;
            }
            cursor++;
        }

        for (int i = index1; i < len1; i++) {
            array[cursor] = nums1[i];
            cursor++;
        }
        for (int i = index2; i < len2; i++) {
            array[cursor] = nums2[i];
            cursor++;
        }
        int len = array.length;
        int mid = len / 2;
        if (len % 2 == 0) {
            return (array[mid] + array[mid + 1]) >> 1;
        } else {
            return array[mid];

        }
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{1,2};
        int[] num2 = new int[]{3,4};
        findMedianSortedArrays(num1,num2);
    }
}
