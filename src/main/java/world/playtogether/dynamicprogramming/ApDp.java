package world.playtogether.dynamicprogramming;

import java.util.Arrays;

/**
 * <project> algoPractice
 *
 * <p> 动态规划
 *
 * @author penggs
 * @since 2020-09-23
 */
public class ApDp {
    /**
     * 最长增长子序列长度
     * 利用数学归纳法思路
     * @param nums 数组
     * @return
     */
    public int lengthOfLongestIncreasingSubsequence(int[] nums) {
        // dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度。
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            // 已知dp[0]...dp[i-1]，求dp[i]
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 获取最大的
        int res = 0;
        for (int value : dp) {
            res = Math.max(res, value);
        }
        return res;
    }

    /**
     * 最大子数组
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * @param nums 数组
     * @return
     */
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        // dp[i]定义为以 nums[i] 为结尾的 最大子数组和
        int[] dp = new int[length];
        dp[0] = nums[0];
        for (int i = 1; i < length; i++) {
            // 单独一个或者与前面的连接起来，取大值
            dp[i] = Math.max(nums[i], nums[i] + dp[i-1]);
        }
        // 获取最大的
        int res = Integer.MIN_VALUE;
        for (int value : dp) {
            res = Math.max(res, value);
        }
        return res;
    }

    public static void main(String[] args) {
        ApDp apDp = new ApDp();
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(apDp.lengthOfLongestIncreasingSubsequence(nums));
        int[] nums2 = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(apDp.maxSubArray(nums2));
    }
}