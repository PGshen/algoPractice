package world.playtogether.tree;

/**
 * <project> algoPractice
 *
 * <p> 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * @author penggs
 * @since 2020-09-13
 */
public class ApLowestCommonAncestor<T> {

    public Node<T> lowestCommAncestor(Node<T> root, Node<T> p, Node<T> q) {
        // 当前节点为空或等于p或等于q时返回当前节点
        if (root == null || root == p || root == q) return root;
        Node<T> left = lowestCommAncestor(root.left, p, q);
        Node<T> right = lowestCommAncestor(root.right, p, q);
        // 分情况
        // 1. 左右子树均不为空，说明p,q分布与root的两侧
        // 2. 左子树为空
        // 3. 右子树为空
        return left == null ? right : right == null ? left : root;
    }

    static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(){}
    }
}