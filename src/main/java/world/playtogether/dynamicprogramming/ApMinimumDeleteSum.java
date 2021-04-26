package world.playtogether.dynamicprogramming;

/**
 * <project> algoPractice
 *
 * <p> 两个字符串的最小ASCII删除和
 * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
 *
 * @author penggs
 * @since 2021-04-25 20:25
 */
public class ApMinimumDeleteSum {

    /**
     * 删除完成后就是最长公共子序列
     * @param a
     * @param b
     * @return
     */
    static int minimumDeleteSum(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];   // dp[i][j]定义为a[0..i]和b[0..j]的转为最长公共子序列最小ASCII删除和
        // 初始化
        for (int i = 1; i < m + 1; i++) {
            dp[i][0] = dp[i-1][0] + a.charAt(i-1);
        }
        for (int j = 1; j < n + 1; j++) {
            dp[0][j] = dp[0][j-1] + b.charAt(j-1);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char cha = a.charAt(i - 1);
                char chb = b.charAt(j - 1);
                if (cha == chb) {
                    // 遇上相等的，追加
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 遇上不相等的，删掉字符，推进a或b,选择较小的
                    dp[i][j] = Math.min(dp[i][j-1] + chb, dp[i-1][j] + cha);
                }
            }
        }
        return dp[m][n];
    }
}