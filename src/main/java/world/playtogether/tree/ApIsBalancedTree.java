package world.playtogether.tree;

/**
 * <project> algoPractice
 *
 * <p> 是否是平衡树
 *
 * @author penggs
 * @since 2021-02-28
 */
public class ApIsBalancedTree {

    /**
     * 暴力法，直接通过比较左右子树的高度进行判断
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        // 判断当前节点和递归的判断左右子树是否是平衡
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    // 计算树的高度
    int depth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    public boolean isBalanced2(TreeNode root) {
        return recur(root) != -1;
    }

    /**
     * 后序遍历，通过判断左右子树的高度差，如果小于2，返回树高便于后续比较；否则返回-1，表示已经不平衡了
     * @param root
     * @return
     */
    private int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left);
        if(left == -1) return -1;
        int right = recur(root.right);
        if(right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}