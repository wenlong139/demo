package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qinwenlong
 * @Date 2021/11/28
 **/
public class PrintMatrixSolution {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> arr = new ArrayList<>();
        int left = 0, right = matrix[0].length - 1;
        int top = 0, down = matrix.length - 1;

        while (true) {
            for (int i = left; i <= right; ++i) {
                arr.add(matrix[top][i]);
            }
            top++;
            if (top > down) break;
            for (int i = top; i <= down; ++i) {
                arr.add(matrix[i][right]);
            }
            right--;
            if (left > right) break;
            for (int i = right; i >= left; --i) {
                arr.add(matrix[down][i]);
            }
            down--;
            if (top > down) break;
            for (int i = down; i >= top; --i) {
                arr.add(matrix[i][left]);
            }
            left++;
            if (left > right) break;

        }
        return arr;
    }

    public static void main(String[] args) {
        char c ='1';
        boolean a = Character.isDigit(c);
        System.out.println(a);
        PrintMatrixSolution solution = new PrintMatrixSolution();
        int[][]matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        solution.spiralOrder(matrix);
    }
}
