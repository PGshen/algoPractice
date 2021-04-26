package world.playtogether.dynamicprogramming;

import java.util.Arrays;

/**
 * <project> algoPractice
 *
 * <p> 跳跃游戏
 *
 * @author penggs
 * @since 2021-04-26 14:03
 */
public class ApJumpGame {
    public static int jump(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        Arrays.fill(dp, len);
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            for (int j = i-1; j >= 0; j--) {
                if (nums[j] >= i - j) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[len-1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};
        System.out.println(jump(nums));
    }
}