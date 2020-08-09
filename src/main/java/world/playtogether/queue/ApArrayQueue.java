package world.playtogether.queue;

/**
 * <project> algoPractice
 *
 * <p> 顺序队列
 *
 * @author penggs
 * @since 2020-08-08 23:16
 */
public class ApArrayQueue<T> {
    private Object[] container;
    private int size = 0;   // 队列当前大小
    private int capacity;   // 队列容量
    private int head = 0;   // 队头下标
    private int tail = 0;   // 队尾下标

    public ApArrayQueue() {
        this.capacity = 10;
        container = new Object[10];
    }

    public ApArrayQueue(int capacity) {
        this.capacity = capacity;
        container = new Object[capacity];
    }

    /**
     * 入队
     *
     * @param data 数据
     * @return boolean 是否成功
     * @author penggs
     * @since 2020/8/8
     */
    public boolean enqueue(T data) {
        if (tail == capacity) {
            if (head == 0) {
                // 队列已满
                return false;
            }
            if (tail - head >= 0) System.arraycopy(container, head, container, 0, tail - head);
            tail -= head;
            head = 0;
        }
        container[tail] = data;
        tail++;
        size++;
        return true;
    }

    /**
     * 出队
     *
     * @return T 数据
     * @author penggs
     * @since 2020/8/8
     */
    public T dequeue() {
        if (head == tail) {
            return null;
        }
        size--;
        return (T) container[head++];
    }

    public int getSize() {
        return size;
    }

    /**
     * 查看队头
     *
     * @return T
     * @author penggs
     * @since 2020/8/8
     */
    public T getHead() {
        if (head == tail) {
            return null;
        }
        return (T) container[head];
    }

    /**
     * 查看队尾
     *
     * @return T
     * @author penggs
     * @since 2020/8/8
     */
    public T getTail() {
        if (head == tail) {
            return null;
        }
        return (T) container[tail - 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = head; i < tail; i++) {
            sb.append(container[i].toString()).append(",");
        }
        return sb.toString().substring(0, sb.toString().lastIndexOf(","));
    }

    public static void main(String[] args) {
        ApArrayQueue<String> queue = new ApArrayQueue<>(3);
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