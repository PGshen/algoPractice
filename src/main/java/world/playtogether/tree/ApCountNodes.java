package world.playtogether.tree;

/**
 * <project> algoPractice
 *
 * <p> 计算完全二叉树的节点数
 *
 * @author penggs
 * @since 2021-02-28
 */
public class ApCountNodes {

    /**
     * 暴力递归
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * 利用完全二叉树的性质进行计算
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            // 左右子树高度一样，那左子树一定是满二叉树，节点数为2^left个，然后递归右子树进行计算
            return countNodes2(root.right) + (1<<left);
        }else{
            // 左右子树高度不一样，那右子树一定是满二叉树，节点数为2^right个，然后递归左子树进行计算
            return countNodes2(root.left) + (1<<right);
        }
    }

    // 计算树高
    private int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
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