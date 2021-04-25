package world.playtogether.dynamicprogramming;

import java.util.Arrays;

/**
 * <project> algoPractice
 *
 * <p> 最长公共子序列的长度
 *
 * @author penggs
 * @since 2021-04-25 19:15
 */
public class ApLongestCommonSubsequence {

    public static int longestCommonSubsequence(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];     // 定义dp[i][j]为a[0..i]和b[0..j]的最长公共子序列的长度
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 最长公共子序列
     *
     * @param a
     * @param b
     * @return
     */
    public static String longestCommonSubsequence2(String a, String b) {
        int m = a.length(), n = b.length();
        String[][] dp = new String[m + 1][n + 1];   // dp[i][j]定义为a[0..i]和b[0..j]的最长公共子序列
        for (int i = 0; i < m + 1; i++) {
            Arrays.fill(dp[i], "");
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char cha = a.charAt(i - 1);
                char chb = b.charAt(j - 1);
                if (cha == chb) {
                    // 遇上相等的，追加
                    dp[i][j] = dp[i - 1][j - 1] + cha;
                } else {
                    // 遇上不相等的，推进a或b,记录较大的
                    dp[i][j] = dp[i - 1][j].length() > dp[i][j - 1].length() ? dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }


    public static void main(String[] args) {
        String a = "zabcde";
        String b = "acez";
        System.out.println(longestCommonSubsequence(a, b));
        System.out.println(longestCommonSubsequence2(a, b));
    }
}