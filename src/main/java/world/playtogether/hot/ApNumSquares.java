package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p>
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * @author penggs
 * @since 2021-01-10
 */
public class ApNumSquares {
    /**
     * 动态规划
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++) {
            // 初始化为最差的情况，都为1时的和
            dp[i] = i;
            // 向前循环判断，减去j的平方看个数是否变少
            for(int j = 1; i - j*j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int size = new ApNumSquares().numSquares(13);
        System.out.println(size);
    }
}