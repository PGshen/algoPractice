package world.playtogether.tree;

/**
 * <project> algoPractice
 *
 * <p> 二叉树节点
 *
 * @author penggs
 * @since 2020-09-20
 */
public class BiNode<T> {
    T data;
    BiNode<T> left;
    BiNode<T> right;

    public BiNode(T data, BiNode<T> left, BiNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BiNode(T data) {
        this.data = data;
    }

    public BiNode(){}
}