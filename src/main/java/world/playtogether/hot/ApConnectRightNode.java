package world.playtogether.hot;

import java.util.LinkedList;

/**
 * <project> algoPractice
 *
 * <p> 连接下一个右节点
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点.
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * @author penggs
 * @since 2021-01-28
 */
public class ApConnectRightNode {
    /**
     * 通过层级遍历，借助队列实现连接
     * @param root
     * @return
     */
    public Node connect1(Node root) {
        if(root==null) {
            return root;
        }
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(queue.size()>0) {
            int size = queue.size();
            //将队列中的元素串联起来
            Node tmp = queue.get(0);
            for(int i=1;i<size;++i) {
                tmp.next = queue.get(i);
                tmp = queue.get(i);
            }
            //遍历队列中的每个元素，将每个元素的左右节点也放入队列中
            for(int i=0;i<size;++i) {
                tmp = queue.remove();
                if(tmp.left!=null) {
                    queue.add(tmp.left);
                }
                if(tmp.right!=null) {
                    queue.add(tmp.right);
                }
            }
        }
        return root;
    }

    /**
     * 类似层级遍历，但是不使用队列，而是记录父节点
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if(root==null) {
            return root;
        }
        Node pre = root;
        //循环条件是当前节点的left不为空，当只有根节点
        //或所有叶子节点都出串联完后循环就退出了
        while(pre.left!=null) {
            Node tmp = pre;
            while(tmp!=null) {
                //将tmp的左右节点都串联起来
                //注:外层循环已经判断了当前节点的left不为空
                tmp.left.next = tmp.right;
                //下一个不为空说明上一层已经帮我们完成串联了
                if(tmp.next!=null) {
                    tmp.right.next = tmp.next.left;
                }
                //继续右边遍历
                tmp = tmp.next;
            }
            //从下一层的最左边开始遍历
            pre = pre.left;
        }
        return root;
    }

    /**
     * 递归方式
     * @param root
     * @return
     */
    public Node connect(Node root) {
        dfs(root);
        return root;
    }

    void dfs(Node root) {
        if(root==null) {
            return;
        }
        Node left = root.left;
        Node right = root.right;
        //以root为起点，将整个纵深这段串联起来
        while(left!=null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }
        //递归的调用左右节点，完成同样的纵深串联
        dfs(root.left);
        dfs(root.right);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}