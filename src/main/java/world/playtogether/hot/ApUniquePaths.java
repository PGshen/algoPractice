package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 不同路径
 * 一个机器人位于一个 m x n 网格的左上角。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角。
 *
 * 问总共有多少条不同的路径？
 *
 * @author penggs
 * @since 2020-11-25
 */
public class ApUniquePaths {
    /**
     * 动态规划解法
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        // 定义dp为到达该点的不同路径数
        int[][] dp = new int[m][n];
        // base case
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 基于已知推出未知的
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 到达当前点的路径数 为从左侧 和 从上侧 过来的路径数之和
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
    }
}