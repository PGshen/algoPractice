package world.playtogether.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 生成所有的二叉搜索树
 *
 * @author penggs
 * @since 2021-03-07
 */
public class ApGenerateTrees {
    public List<TreeNode> generateTrees(int n) {
        if(n < 1)
            return new ArrayList<>();
        return generate(1, n);
    }

    List<TreeNode> generate(int start, int end) {
        // 写在这里保证结果是当前长度的，而不是所有的
        List<TreeNode> ret = new ArrayList<>();
        if (start > end) {
            // 这里保证非空数组，符合下面的循环
            ret.add(null);
            return ret;
        }
        // 选取任意一个节点作为当前节点，然后递归左右子树
        for (int i = start; i <= end; i++) {
            // 因为是按顺序的，start到i-1的左子树的，i+1到end是右子树的
            List<TreeNode> lefts = generate(start, i-1);
            List<TreeNode> rights = generate(i+1, end);
            // 左右子树集合叉乘
            for (TreeNode left: lefts) {
                for (TreeNode right: rights) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    ret.add(node);
                }
            }
        }
        return ret;
    }

    // 动态规划方式统计个数
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 0; j < i; j++) {
                // 左右叉乘，再累加
                dp[i] += dp[j] * dp[i-j+1];
            }
        }
        return dp[n+1];
    }
}