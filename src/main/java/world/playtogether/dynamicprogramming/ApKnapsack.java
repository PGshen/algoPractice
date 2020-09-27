package world.playtogether.dynamicprogramming;

import java.util.Arrays;

/**
 * <project> algoPractice
 *
 * <p> 动规-背包
 *
 * @author penggs
 * @since 2020-09-26
 */
public class ApKnapsack {
    /**
     * 给你一个可装载重量为c的背包和若干个物品，每个物品有重量和价值两个属性。其中第i个物品的重量为w[i]，价值为v[i]，现在让你用这个背包装物品，最多能装的价值是多少？
     * <p>
     * 用F(n,c)表示将前n个物品放进容量为c的背包里，得到的最大的价值
     * 用自顶向下的角度来看，假如我们已经进行到了最后一步（即求解将n nn个物品放到背包里获得的最大价值），此时我们便有两种选择
     * 1. 不放第n个物品，此时总价值为F(n-1,c)
     * 2. 放第n个物品，此时总价值为v[n] + F(n-1, c - w[n])
     * 总后选择价值最大的那个方案，得到递推公式
     * F(i,c) = max(F(i-1, c), v[i]+F(i-1, c - w[i]))
     *
     * @param w 物品重量
     * @param v 物品价值
     * @param c 容量
     * @return
     */
    public static int knapsack(int[] w, int[] v, int c) {
        return ks(w, v, w.length - 1, c);
    }

    public static int ks(int[] w, int[] v, int index, int capacity) {
        // 全部已完成或者剩余容量不足是返回0
        if (index < 0 || capacity <= 0) {
            return 0;
        }
        // 不装入第index个物品
        int res = ks(w, v, index - 1, capacity);
        // 可以装入第index个物品
        if (w[index] <= capacity) {
            // 选取大的
            res = Math.max(res, v[index] + ks(w, v, index - 1, capacity - w[index]));
        }
        return res;
    }

    // 备忘录
    private static int[][] memo;

    /**
     * 递归方法，加上备忘录，消除重复计算
     *
     * @param w
     * @param v
     * @param c
     * @return
     */
    public static int knapsack2(int[] w, int[] v, int c) {
        memo = new int[w.length][c + 1];
        return ks(w, v, w.length - 1, c);
    }

    public static int ks2(int[] w, int[] v, int index, int capacity) {
        // 全部已完成或者剩余容量不足是返回0
        if (index < 0 || capacity <= 0) {
            return 0;
        }
        // 查找备忘录
        if (memo[index][capacity] != 0) {
            return memo[index][capacity];
        }
        // 不装入第index个物品
        int res = ks(w, v, index - 1, capacity);
        // 可以装入第index个物品
        if (w[index] <= capacity) {
            // 选取大的
            res = Math.max(res, v[index] + ks(w, v, index - 1, capacity - w[index]));
        }
        // 写入备忘录
        memo[index][capacity] = res;
        return res;
    }

    /**
     * 动态规划解法，将备忘录转换为dp table，从前向后推
     *
     * @param w
     * @param v
     * @param c
     * @return
     */
    public static int knapsack3(int[] w, int[] v, int c) {
        if (w.length == 0) return 0;
        // dp记录前i个物品重量为w是的最大价值
        int[][] dp = new int[w.length][c + 1];
        // base case, 第0个
        for (int i = 0; i <= c; i++) {
            dp[0][i] = w[0] <= i ? v[0] : 0;
        }
        // 基于base case向后递推
        for (int i = 1; i < w.length; i++) {
            for (int j = 0; j <= c; j++) {
                // 不放入，同一面的一样
                dp[i][j] = dp[i - 1][j];
                // 放入，选取大的
                if (w[i] <= j) {
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i - 1][j - w[i]]);
                }
            }
        }
        return dp[w.length - 1][c];
    }

    /**
     * 动态规划解法，对dp表进行压缩，空间复杂度降低
     * 因为向后推的时，只需要用到当前位置和该位置之前的值，因此可以使用一维的数据保存状态
     *
     * @param w
     * @param v
     * @param c
     * @return
     */
    public static int knapsack4(int[] w, int[] v, int c) {
        if (w.length == 0) return 0;
        // dp记录前i个物品重量为w是的最大价值
        int[] dp = new int[c + 1];
        // base case, 第0个
        for (int i = 0; i <= c; i++) {
            dp[i] = w[0] <= i ? v[0] : 0;
        }
        // 基于base case向后递推
        for (int i = 1; i < w.length; i++) {
            // 这里从后向前推，防止结果被覆盖，因为计算后面的需要用到前面的，如果前面的修改过，则会导致后面的计算不对
            for (int j = c; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[c];
    }

    public static void main(String[] args) {
        int[] w = {2, 1, 3, 2};
        int[] v = {12, 10, 20, 15};
        System.out.println(knapsack(w, v, 5));
        System.out.println(knapsack2(w, v, 5));
        System.out.println(knapsack3(w, v, 5));
        System.out.println(knapsack4(w, v, 5));
    }
}