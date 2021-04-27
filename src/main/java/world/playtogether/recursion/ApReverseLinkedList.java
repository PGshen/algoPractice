package world.playtogether.recursion;

import world.playtogether.list.ApDoubleLinkedList;

/**
 * <project> algoPractice
 *
 * <p> 递归反正链表
 *
 * @author penggs
 * @since 2020-09-21
 */
public class ApReverseLinkedList<T> {

    /**
     * 反转链表
     * @param head
     * @return
     */
    Node<T> reverse(Node<T> head) {
        if (head.next == null) return head;
        Node<T> last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    /**
     * 反正前N个节点
     */
    Node<T> successor = null;
    Node<T> reverseN(Node<T> head, int n) {
        if (n == 1) {
            // 到达第N个节点，记录前驱节点，并开始返回递归
            successor = head.next;
            return head;
        }
        Node<T> last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return last;
    }

    Node<T> reverseN2(Node<T> head, int n) {
        if (head == null) return null;
        Node<T> cur = head, prev = null;
        while (n > 0 && cur != null) {
            Node<T> next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            n--;
        }
        head.next = cur;    // 旧的头节点连接后面没有反转的后续链表
        return prev;
    }

    /**
     * 反正链表区间[m,n]的节点
     * @param head
     * @param m
     * @param n
     * @return
     */
    Node<T> reverseMN(Node<T> head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        // 向前直到m==1时，转为reverseN情况
        head.next = reverseMN(head.next, m - 1, n - 1);
        return head;
    }

    Node<T> reverseMN2(Node<T> head, int m, int n) {
        if (head == null) return null;
        Node<T> cur = head, prev = null, nodeM = null;
        while (cur != null) {
            if (--m > 0) {
                // 还没到达m，直接前进，并记录m节点
                prev = cur;
                cur = cur.next;
                nodeM = prev;
                n--;
            } else if (n-- > 0) {
                // 在[m,n]范围内，反转
                Node<T> next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            } else {
                break;
            }
        }
        if (prev != null) {
            // 前后指针修改
            nodeM.next.next = cur;
            nodeM.next = prev;
        }
        return head;
    }

    public void print(Node<T> head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public static void main(String[] args) {
        Node<Integer> node5 = new Node<>(5);
        Node<Integer> node4 = new Node<>(4, node5);
        Node<Integer> node3 = new Node<>(3, node4);
        Node<Integer> node2 = new Node<>(2, node3);
        Node<Integer> node1 = new Node<>(1, node2);
        ApReverseLinkedList<Integer> reverseLinkedList = new ApReverseLinkedList<>();
        //reverseLinkedList.print(reverseLinkedList.reverse(node1));
        //reverseLinkedList.print(reverseLinkedList.reverseN(node1, 3));
        //reverseLinkedList.print(reverseLinkedList.reverseN2(node1, 3));
        //reverseLinkedList.print(reverseLinkedList.reverseMN(node1, 2, 4));
        reverseLinkedList.print(reverseLinkedList.reverseMN2(node1, 2, 4));
    }
}