package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 找零钱
 *
 * @author penggs
 * @since 2021-01-12
 */
public class ApCoinChange {
    public int coinChange(int[] coins, int amount) {
        // 定义dp数组为总金额为i时的最小硬币数量
        int[] dp = new int[amount+1];
        for (int i = 0; i < amount + 1; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            for (int coin: coins) {
                // dp[i - coin] != Integer.MAX_VALUE 表示前面的有解
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = new int[] {2, 5, 10, 1};
        //int[] coins = new int[] {2};
        System.out.println(new ApCoinChange().coinChange(coins, 27));
    }
}