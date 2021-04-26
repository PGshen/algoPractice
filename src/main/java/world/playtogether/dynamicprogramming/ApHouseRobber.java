package world.playtogether.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <project> algoPractice
 *
 * <p> 打家劫舍
 *
 * @author penggs
 * @since 2020-09-23
 */
public class ApHouseRobber {
    int[] memo;

    /**
     * 房屋是一排的，数组记录每个房屋存放的金额，盗贼不能同时抢连续的两个房屋，求最大可抢的金额
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(nums, 0);
    }

    /**
     * 自顶向下递归解法
     * @param nums
     * @param start 第
     * @return
     */
    private int dp(int[] nums, int start) {
        // 超出最后一家
        if (start >= nums.length) {
            return 0;
        }
        if (memo[start] != -1) return memo[start];
        // 当前不抢，到下一家；当前抢，到下下家
        int res = Math.max(dp(nums, start+1), nums[start] + dp(nums, start+2));
        memo[start] = res;
        return res;
    }

    /**
     * 自底向上迭代解法
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int length = nums.length;
        // dp[] 定义为 从第 i 间房子开始抢劫，最多能抢到的钱为 x
        // base case: dp[n] = 0
        int[] dp = new int[length + 2];
        for (int i = length - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i+1], nums[i] + dp[i+2]);
        }
        return dp[0];
    }

    /**
     * 自底向上迭代解法,并进行状态压缩
     * @param nums
     * @return
     */
    public int rob3(int[] nums) {
        int length = nums.length;
        // 分别保存dp[i+1], dp[i+2]和dp[i]
        int dp_i1 = 0, dp_i2 = 0;
        int dp_i = 0;
        for (int i = length - 1; i >= 0; i--) {
            dp_i = Math.max(dp_i1, nums[i] + dp_i2);
            dp_i2 = dp_i1;
            dp_i1 = dp_i;
        }
        return dp_i;
    }

    /**
     * 房屋是一圈的，数组记录每个房屋存放的金额，盗贼不能同时抢连续的两个房屋，求最大可抢的金额
     * @param nums
     * @return
     */
    public int rob4(int[] nums) {
        int length = nums.length;
        if (length == 1) return nums[0];
        // 首尾房间不能同时被抢，那么只可能有三种不同情况：要么都不被抢；要么第一间房子被抢最后一间不抢；要么最后一间房子被抢第一间不抢
        // 由于金额不会是负数，所以只需考虑后面两种情况即可
        return Math.max(dpRange(nums, 0, length - 1), dpRange(nums, 1, length));
    }

    private int dpRange(int[] nums, int start, int end) {
        // 分别保存dp[i+1], dp[i+2]和dp[i]
        int dp_i1 = 0, dp_i2 = 0;
        int dp_i = 0;
        for (int i = end - 1; i >= start; i--) {
            dp_i = Math.max(dp_i1, nums[i] + dp_i2);
            dp_i2 = dp_i1;
            dp_i1 = dp_i;
        }
        return dp_i;
    }

    Map<TreeNode, Integer> memoMap = new HashMap<>();

    /**
     * 房屋是二叉树排列的，记录每个房屋存放的金额，盗贼不能同时抢连续的两个房屋，求最大可抢的金额
     * @param root
     * @return
     */
    public int rob5(TreeNode root) {
        if (root == null) return 0;
        // 利用备忘录消除重叠子问题
        if (memoMap.containsKey(root))
            return memoMap.get(root);
        // 抢，然后去下下家
        int do_it = root.val
                + (root.left == null ?
                0 : rob5(root.left.left) + rob5(root.left.right))
                + (root.right == null ?
                0 : rob5(root.right.left) + rob5(root.right.right));
        // 不抢，然后去下家
        int not_do = rob5(root.left) + rob5(root.right);

        int res = Math.max(do_it, not_do);
        memoMap.put(root, res);
        return res;
    }

    /**
     * 二叉树排列的房屋，不使用备忘录的情况下
     * @param root
     * @return
     */
    public int rob6(TreeNode root) {
        int[] res = dpTree(root);
        return Math.max(res[0], res[1]);
    }

    /* 返回一个大小为 2 的数组 arr
    arr[0] 表示不抢 root 的话，得到的最大钱数
    arr[1] 表示抢 root 的话，得到的最大钱数 */
    int[] dpTree(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};
        int[] left = dpTree(root.left);
        int[] right = dpTree(root.right);
        // 抢，下家就不能抢了
        int rob = root.val + left[0] + right[0];
        // 不抢，下家可抢可不抢，取决于收益大小
        int not_rob = Math.max(left[0], left[1])
                + Math.max(right[0], right[1]);

        return new int[]{not_rob, rob};
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public static void main(String[] args) {
        ApHouseRobber apHouseRobber = new ApHouseRobber();
        int[] nums = new int[]{1,2,3,1};
        System.out.println(apHouseRobber.rob4(nums));
    }
}