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
     * <p>
     * 状态转移⽅程：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * <p>
     * k固定为1，可以将三位数组转为二位数组
     *
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

    /**
     * 最大收益
     *
     * @param prices 价格
     * @param K      限制交易次数
     * @return
     */
    public static int maxProfit(int[] prices, int K) {
        int n = prices.length;
        // 定义dp[i][k][s]为第i天最多k次的最大收益，[s]表示是否持有
        int[][][] dp = new int[n][K+1][2];
        for (int i = 0; i < n; i++) {
            // base case：k = 0, 即不能进行交易
            dp[i][0][0] = 0;
            dp[i][0][1] = Integer.MIN_VALUE;    // 这种情况实际不存在
        }
        for (int k = 0; k <= K; k++) {
            // base case: i = 0, 即第一天是否买入的情况
            dp[0][k][0] = 0;
            dp[0][k][1] = -prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= K; k++) {
                // 今天不持有：昨天就没有持有或者昨天持有今天卖出（交易次数不用减）
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                // 今天持有：昨天就持有或者昨天没有持有今天买入（交易次数减一）
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][K][0];
    }

    public static void main(String[] args) {
        int[] prices = new int[]{3, 2, 6, 5, 0, 3};
        System.out.println(maxProfit(prices, 2));
    }
}