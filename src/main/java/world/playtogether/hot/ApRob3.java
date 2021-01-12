package world.playtogether.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍3
 * 树形结构
 */
public class ApRob3 {

    /**
     * 暴力递归
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if(root == null) return 0;
        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }
        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }
        return Math.max(money, rob(root.left) + rob(root.right));
    }

    /**
     * 带备忘录的
     * @param root
     * @return
     */
    public int rob2(TreeNode root) {
        HashMap<TreeNode, Integer> meno = new HashMap<>();
        return robInternal(root, meno);
    }

    public int robInternal(TreeNode root, Map<TreeNode, Integer> memo) {
        if(root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);

        int money = root.val;
        if (root.left != null) {
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }

    public int rob3(TreeNode root) {
        // 数组用于保存是 当前节点偷还是不偷时所能获得的最大金额
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        // 父节点不偷，两个孩子可选择偷或者不偷
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 父节点偷，两个孩子节点只能不偷
        result[1] = left[0] + right[0] + root.val;

        return result;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
