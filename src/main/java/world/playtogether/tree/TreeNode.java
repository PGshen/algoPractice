package world.playtogether.tree;

/**
 * <project> algoPractice
 *
 * <p> 二叉树
 *
 * @author penggs
 * @since 2021-03-07
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

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