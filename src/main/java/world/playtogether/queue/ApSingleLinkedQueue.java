package world.playtogether.queue;

/**
 * <project> algoPractice
 *
 * <p> 链式单向队列
 *
 * @author penggs
 * @since 2020-08-08 23:16
 */
public class ApSingleLinkedQueue<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;
    
    public boolean enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }
    
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        if (size == 1) {
            tail = null;
        }
        Node<T> p = head;
        head = head.next;
        p.next = null;
        return p.data;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public T getHead() {
        return head.data;
    }
    
    public T getTail() {
        return tail.data;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> p = head;
        while (p != null) {
            sb.append(p.data.toString()).append(",");
            p = p.next;
        }
        return sb.toString().substring(0, sb.toString().lastIndexOf(","));
    }

    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        ApSingleLinkedQueue<String> queue = new ApSingleLinkedQueue<>();
        queue.enqueue("One");
        queue.enqueue("Two");
        queue.enqueue("Three");
        System.out.println(queue);
        System.out.println(queue.enqueue("Four"));
        System.out.println(queue.dequeue());
        queue.enqueue("Five");
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("Six");
        System.out.println(queue);
        System.out.println(queue.getHead());
        System.out.println(queue.getTail());
    }
}