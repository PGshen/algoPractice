package world.playtogether.tree;

/**
 * <project> algoPractice
 *
 * <p> 求根节点到叶节点数字之和
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author penggs
 * @since 2021-04-01
 */
public class ApSumNumbers {
    int ret = 0;
    // 回溯法
    public int sumNumbers(TreeNode root) {
        backtrack(root, 0);
        return ret;
    }

    // sum 为上一层的和
    void backtrack(TreeNode root, int sum) {
        if(root == null) return;
        // 计算到达当前节点的和
        sum = sum * 10 + root.val;
        // 到达叶子节点了，加入结果集
        if(root.left == null && root.right == null) ret += sum;
        backtrack(root.left, sum);
        backtrack(root.right, sum);
    }
}