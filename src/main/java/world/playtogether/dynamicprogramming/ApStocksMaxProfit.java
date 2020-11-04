package world.playtogether.dynamicprogramming;

/**
 * <project> algoPractice
 *
 * <p> 股票最大收益
 *
 * @author penggs
 * @since 2020-11-04
 */
public class ApStocksMaxProfit {
    /**
     * dp定义状态，为第i天最多交易k次，当前手上是否持有股票的最多收益
     * base case：
     * dp[-1][k][0] = dp[i][0][0] = 0
     * dp[-1][k][1] = dp[i][0][1] = -infinity
     *
     * 状态转移⽅程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     * k固定为1，可以将三位数组转为二位数组
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0; // 解释： // dp[i][0] // = max(dp[-1][0], dp[-1][1] + prices[i]) // = max(0, -infinity + prices[i]) = 0
                dp[i][1] = -prices[i]; //解释： // dp[i][1] // = max(dp[-1][1], dp[-1][0] - prices[i]) // = max(-infinity, 0 - prices[i]) // = -prices[i]
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }
}