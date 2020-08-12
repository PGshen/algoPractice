package world.playtogether.tree;

import world.playtogether.queue.ApArrayQueue;
import world.playtogether.queue.ApSingleLinkedQueue;

/**
 * <project> algoPractice
 *
 * <p> 二叉查找树
 *
 * @author penggs
 * @since 2020-08-12 19:02
 */
public class ApBinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    /**
     * 查找
     *
     * @param data 值
     * @return world.playtogether.tree.ApBinarySearchTree.Node<T>
     * @author penggs
     * @since 2020/8/12
     */
    public Node<T> find(T data) {
        Node<T> p = root;
        while (p != null) {
            if (p.data.compareTo(data) == 0) {
                return p;
            } else if (p.data.compareTo(data) < 0) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        return null;
    }

    /**
     * 插入
     *
     * @param data 值
     * @author penggs
     * @since 2020/8/12
     */
    public void insert(T data) {
        if (root == null) {
            root = new Node<>(data);
            return;
        }
        Node<T> p = root;
        // 直到找到位置
        while (true) {
            if (p.data.compareTo(data) < 0) {
                if (p.right == null) {
                    p.right = new Node<>(data);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new Node<>(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    /**
     * 删除
     *
     * @param data 值
     * @return boolean
     * @author penggs
     * @since 2020/8/12
     */
    public boolean delete(T data) {
        Node<T> p = root;
        Node<T> pp = null;
        // 找到待删除节点p, pp为p的父节点
        while (p != null && p.data.compareTo(data) != 0) {
            pp = p;
            if (p.data.compareTo(data) < 0) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        if (p == null) {
            // 未找到
            return true;
        }
        // 待删除节点，分情况讨论
        // 1. 有两个子节点
        if (p.left != null && p.right != null) {
            // 找到右子树的最小节点
            Node<T> minp = p.right;
            Node<T> minpp = p;
            while (minp.left != null) {
                minpp = minp;
                minp = minp.left;
            }
            p.data = minp.data;
            // 统一转为删除p节点，minp节点不可能有两个子节点
            p = minp;
            pp = minpp;
        }
        // 2. 只有一个子节点,或者叶子节点
        Node<T> child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        if (pp == null) {
            // pp为null时，说明删除的是根节点
            root = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else if (pp.right == p) {
            pp.right = child;
        }
        return true;
    }

    /**
     * 树高
     *
     * @param root 根节点
     * @return int
     * @author penggs
     * @since 2020/8/12
     */
    public int maxHeight(Node<T> root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxHeight(root.left), maxHeight(root.right)) + 1;
    }

    public Node<T> findMin() {
        if (root == null) {
            return null;
        }
        Node<T> p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public Node<T> findMax() {
        if (root == null) {
            return null;
        }
        Node<T> p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    /**
     * 前序遍历
     *
     * @param root 根节点
     * @author penggs
     * @since 2020/8/12
     */
    public void preTravel(Node<T> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preTravel(root.left);
        preTravel(root.right);
    }

    /**
     * 中序遍历
     *
     * @param root 根节点
     * @author penggs
     * @since 2020/8/12
     */
    public void inTravel(Node<T> root) {
        if (root == null) {
            return;
        }
        inTravel(root.left);
        System.out.print(root.data + " ");
        inTravel(root.right);
    }

    /**
     * 后续遍历
     *
     * @param root 根节点
     * @author penggs
     * @since 2020/8/12
     */
    public void postTravel(Node<T> root) {
        if (root == null) {
            return;
        }
        postTravel(root.left);
        postTravel(root.right);
        System.out.print(root.data + " ");
    }

    /**
     * 层次遍历
     *
     * @param root 根节点
     * @author penggs
     * @since 2020/8/12
     */
    public void levelTravel(Node<T> root) {
        if (root == null) {
            return;
        }
        // 借助队列实现
        ApSingleLinkedQueue<Node<T>> queue = new ApSingleLinkedQueue<>();
        queue.enqueue(root);
        do {
            Node<T> p = queue.dequeue();
            System.out.print(p.data + " ");
            if (p.left != null) {
                queue.enqueue(p.left);
            }
            if (p.right != null) {
                queue.enqueue(p.right);
            }
        } while (!queue.isEmpty());
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

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static void main(String[] args) {
        ApBinarySearchTree<Integer> tree = new ApBinarySearchTree<>();
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(2);
        tree.insert(1);
        tree.insert(8);
        tree.insert(7);
        tree.inTravel(tree.root);
        System.out.println();
        tree.preTravel(tree.root);
        System.out.println();
        tree.postTravel(tree.root);
        System.out.println();
        tree.levelTravel(tree.root);
        System.out.println();
        tree.delete(3);
        tree.preTravel(tree.root);
        System.out.println();
        tree.delete(4);
        tree.preTravel(tree.root);
        System.out.println();
        System.out.println(tree.find(5));
        System.out.println(tree.maxHeight(tree.root));
    }
}