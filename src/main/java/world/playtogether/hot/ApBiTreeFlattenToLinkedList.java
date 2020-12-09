package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 给定一个二叉树，原地将它展开为一个单链表。
 *
 * @author penggs
 * @since 2020-12-09
 */
public class ApBiTreeFlattenToLinkedList {
    /**
     * 将右子树接到左子树的最右节点，然后将左子树接到根节点的右子树位置，移动点到到右子树，然后重复前面步骤，直到null
     * @param root
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    (left != null ? ", left=" + left.toString() : "") +
                    (right != null ?  ", right=" + right.toString() : "") +
                    '}';
        }
    }
}