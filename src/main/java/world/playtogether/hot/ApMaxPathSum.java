package world.playtogether.hot;

/**
 <project> algoPractice
 *
 * <p> 最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 @author penggs
 @since 2020-12-12
 */
public class ApMaxPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        curNodeSum(root);
        return max;
    }

    public int curNodeSum(TreeNode root) {
        if (root == null) return 0;
        int maxLeft = curNodeSum(root.left);
        int maxRight = curNodeSum(root.right);
        // 判断当前节点作为根节点时是否超过已知的最大路径和
        int curMax = root.val + (Math.max(maxLeft, 0)) + Math.max(maxRight, 0);
        if (curMax > max) max = curMax;
        // NOTE: 作为父节点的子路径时，当前节点只能选择左右中的一个路径
        int curSubMax = root.val + Math.max(maxLeft, maxRight);
        return Math.max(curSubMax, 0);
    }


    static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
}