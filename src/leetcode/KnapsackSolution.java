package leetcode;

/**
 * 背包问题
 *
 * @Author qinwenlong
 * @Date 2021/12/13
 **/
public class KnapsackSolution {

    public static void main(String[] args) {
        int m = 10;
        int n = 3;
        int w[] = {3, 4, 5};
        int p[] = {4, 5, 6};
        int s = zeroOnePack(m, n, w, p);
        System.out.println(s);
    }

    public static int zeroOnePack(int m, int n, int[] weight, int[] price) {
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + price[i - 1]);
                }
            }
        }
        return dp[n][m];
    }
}
