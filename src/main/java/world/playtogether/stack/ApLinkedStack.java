package world.playtogether.stack;

/**
 * <project> algoPractice
 *
 * <p> 链式栈
 *
 * @author penggs
 * @since 2020-08-05 23:21
 */
public class ApLinkedStack<T> {

    private Node<T> top = null;
    private int count;

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        if (top != null) {
            newNode.next = top;
        }
        top = newNode;
    }

    public T pop() {
        if (top == null) {
            return null;
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    public T peek() {
        if (top == null) {
            return null;
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> p = top;
        while (p != null) {
            sb.append(p.data.toString());
            p = p.next;
            if (p != null) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        ApLinkedStack<String> stack = new ApLinkedStack<>();
        stack.push("One");
        stack.push("Two");
        stack.push("Three");
        System.out.println(stack.toString());
        stack.push("Four1");
        stack.push("Four2");
        stack.push("Four3");
        stack.push("Four4");
        System.out.println(stack.peek());
        System.out.println(stack.toString());
        System.out.println(stack.pop());
        System.out.println(stack.toString());
    }
}