package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qinwenlong
 * @Date 2022/4/8
 **/
public class NQueue {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solveNQueens(4);
    }

    static class Solution {
        List<List<String>> res = new ArrayList();

        public List<List<String>> solveNQueens(int n) {
            char[][] array = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    array[i][j] = '.';
                }
            }
            track(array, 0);
            return res;
        }

        public void track(char[][] array, int row) {
            if (row == array.length) {
                List<String> list = new ArrayList();
                for (int i = 0; i < array.length; i++) {
                    list.add(new String(array[i]));
                }
                res.add(list);
            }
            int len = array[0].length;
            for (int col = 0; col < len; col++) {
                if (!isValuid(array, row, col)) {
                    continue;
                }
                array[row][col] = 'Q';
                track(array, row + 1);
                array[row][col] = '.';
            }
        }

        boolean isValuid(char[][] array, int row, int col) {
            int len = array.length;
            // 检查列是否有冲突
            for (int i = 0; i < row; i++) {
                if (array[i][col] == 'Q') {
                    return false;
                }
            }
            // 检查左上斜线
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (array[i][j] == 'Q') {
                    return false;
                }
            }
            // 检查右上斜线
            for (int i = row - 1, j = col + 1; i >= 0 && j < len; i--, j++) {
                if (array[i][j] == 'Q') {
                    return false;
                }
            }
            return true;
        }
    }
}
