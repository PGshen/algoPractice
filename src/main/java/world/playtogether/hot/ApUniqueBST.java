package world.playtogether.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * <project> algoPractice
 *
 * <p> 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * @author penggs
 * @since 2020-11-28
 */
public class ApUniqueBST {
    /**
     * 如果整数 1 - n 中的 k 作为根节点值，则 1 - k-1 会去构建左子树，k+1 - n 会去构建右子树。
     * 左子树出来的形态有 a 种，右子树出来的形态有 b 种，则整个树的形态有 a∗b 种。
     * 以 k 为根节点的 BST 种类数 = 左子树 BST 种类数 * 右子树 BST 种类数以k为根节点的BST种类数=左子树BST种类数∗右子树BST种类数
     * 问题变成：不同的 k 之下，等号右边的乘积，进行累加
     *
     * 动态规划
     * 定义dp[i]：用连着的i个数，所构建出的BST种类数
     *
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        // dp[i] 代表以第 i 个为根节点组成的 BST
        int[] dp = new int[n + 1];

        // 没有节点，只能形成空的 BST
        dp[0] = 1;
        // 只有一个节点，只能形成一颗 BST
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // 左侧 i-1 个节点与右侧 i-j-1 个节点能组成的 BST
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        return dp[n];
    }

    /**
     * 递归解法
     * @param n
     * @return
     */
    public static int numTrees2(int n) {
        if (n == 0 || n == 1) return 1;
        int num = 0;
        for (int i = 0; i < n; i++) {
            // 累加
            num += numTrees2(i) * numTrees2(n-i-1);
        }
        return num;
    }

    /**
     * 带备忘录的递归解法
     * @param n
     * @return
     */
    public static int numTrees3(int n){
        Map<Integer, Integer> memo = new HashMap<>();
        return numTreesSub(n, memo);
    }

    public static int numTreesSub(int n, Map<Integer, Integer> memo){
        if (n == 0 || n == 1) return 1;
        int num = 0;
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        for (int i = 0; i < n; i++) {
            num += numTrees2(i) * numTrees2(n-i-1);
        }
        memo.put(n, num);
        return num;
    }

    public static void main(String[] args) {
        System.out.println(numTrees(3));
        System.out.println(numTrees2(3));
        System.out.println(numTrees3(3));
    }

}