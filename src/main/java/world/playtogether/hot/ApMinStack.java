package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 可以常数获取最小值的栈
 * 思路是每次入栈计算当前节点的最小值，并记录到节点上
 *
 * @author penggs
 * @since 2021-01-04
 */
public class ApMinStack {
    // 用链表的形式
    private Node head;

    /** initialize your data structure here. */
    public ApMinStack() {

    }

    public void push(int x) {
        if(head == null) {
            head = new Node(x, x);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    static class Node {
        int val;
        // 记录当前栈节点的最小值
        int min;
        Node next;

        Node(int val, int min) {
            this.val = val;
            this.min = min;
        }

        Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}