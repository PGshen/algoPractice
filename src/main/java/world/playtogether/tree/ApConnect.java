package world.playtogether.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <project> algoPractice
 *
 * <p> 填充每个节点的下一个右侧节点指针
 *
 * @author penggs
 * @since 2021-03-07
 */
public class ApConnect {

    /**
     * 通过层次遍历
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if(root == null) return root;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            Node node = queue.poll();
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
            for(int i = 1; i < size; i++) {
                Node next = queue.poll();
                node.next = next;
                if(next.left != null) queue.offer(next.left);
                if(next.right != null) queue.offer(next.right);
                node = next;
            }
            node.next = null;
        }
        return root;
    }

    /**
     * 通过指针在链表上移动
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if(root == null) return root;
        Node cur = root;

        // 当一层链表
        // cur在上层移动，pre在下层移动
        while(cur != null) {
            Node pre = new Node();
            Node head = pre;    // head始终能保存了一个层的最左侧的
            while(cur != null) {
                if(cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                if(cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }

                // 在链表上移动
                cur = cur.next;
            }
            // 到下一层
            cur = head.next;
        }

        return root;
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