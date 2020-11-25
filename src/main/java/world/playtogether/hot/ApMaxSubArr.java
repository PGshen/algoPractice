package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 最大子序和
 *
 * @author penggs
 * @since 2020-11-25
 */
public class ApMaxSubArr {
    public static int maxSubArr(int[] arr) {
        int len = arr.length;
        if (len == 0) return 0;
        // 定义dp为 以arr[i]为结尾的最大子序和
        int[] dp = new int[len];
        dp[0] = arr[0];
        // 已知dp[0],...,dp[i-1]计算dp[i]
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(arr[i], arr[i] + dp[i-1]);
        }
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArr(arr));
    }
}