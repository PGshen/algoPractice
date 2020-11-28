package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 镜像二叉树
 *
 * @author penggs
 * @since 2020-11-28
 */
public class ApSymmetricTree {
    /**
     * 递归方法
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    /**
     * 递归判断
     * @param left
     * @param right
     * @return
     */
    public static boolean dfs(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        // 其中一个为空
        if (left == null || right == null) return false;
        // 左右节点不一样
        if (left.val != right.val) return false;
        // 左-左和右-右比较 左-右和右-左比较
        return dfs(left.left, right.right) && dfs(left.right, right.left);
    }

     static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }

         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        System.out.println(isSymmetric(root));
    }
}