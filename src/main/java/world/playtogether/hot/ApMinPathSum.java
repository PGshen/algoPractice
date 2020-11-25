package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 最小路径和
 *
 * @author penggs
 * @since 2020-11-25
 */
public class ApMinPathSum {
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 定义dp为到达该点的最短路径
        int[][] dp = new int[m][n];
        // base case
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        // 基于已知推出未知的
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 到达当前点的最短路径 为从左侧 和 从上侧 过来的路径最小值加上当前点的
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[m-1][n-1];
    }
}