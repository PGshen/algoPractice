package world.playtogether.tree;

/**
 * <project> algoPractice
 *
 * <p> 二叉树
 *
 * @author penggs
 * @since 2020-10-22
 */
public class ApBinaryTree<T extends Comparable<T>> {
    /**
     * 判断两颗树是否相等
     * @param root1
     * @param root2
     * @return
     */
    public boolean isSameTree(BiNode<T> root1, BiNode<T> root2) {
        // 都为空
        if (root1 == null && root2 == null) return true;
        // 其中一个为空
        if (root1 == null || root2 == null) return false;
        // 值不等
        if (root1.data.compareTo(root2.data) != 0) return false;
        return isSameTree(root1.left, root1.right) && isSameTree(root2.left, root2.right);
    }

    /**
     * 判断一棵树是否是搜索树,节点应该要小于右边子树的所有节点,大于左子树的所有节点，因此需要保存最大最小
     * @param root
     * @return
     */
    public boolean isValidBST(BiNode<T> root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(BiNode<T> root, BiNode<T> min, BiNode<T> max) {
        if (root == null) return true;
        if (min != null && root.data.compareTo(min.data) < 0) return false;
        if (max != null && root.data.compareTo(max.data) > 0) return false;

        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }

    long pre = Long.MIN_VALUE;

    /**
     * 中序遍历时，判断当前节点是否大于中序遍历的前一个节点，如果大于，说明满足 BST，继续遍历；否则直接返回 false。
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }

    /**
     * 判断值是否在树上
     * @param root
     * @param val
     * @return
     */
    public boolean isInTree(BiNode<T> root, T val) {
        if (root == null) return false;
        if (root.data.compareTo(val) == 0) return true;
        return isInTree(root.left, val) && isInTree(root.right, val);
    }

    /**
     * 判断值是否在搜索树上
     * @param root
     * @param val
     * @return
     */
    public boolean isInBST(BiNode<T> root, T val) {
        if (root == null) return false;
        if (root.data.compareTo(val) < 0) {
            return isInBST(root.right, val);
        } else if (root.data.compareTo(val) > 0) {
            return isInBST(root.left, val);
        } else {
            return true;
        }
    }
}