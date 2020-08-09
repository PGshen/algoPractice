package world.playtogether.queue;

/**
 * <project> algoPractice
 *
 * <p> 循环队列
 * 循环队列避免了顺序队列的数据搬移，当数据到达队尾时，向前查看是否有空位可入队数据，即循环使用;另外为了区别队满和队空的条件，将会有一致空间不可使用，即浪费了
 * 队列判空条件：head == tail
 * 队列判满条件：(tail + 1) % capacity == tail
 *
 * <p>阻塞队列
 * 其实就是在队列基础上增加了阻塞操作。
 * 简单来说，就是在队列为空的时候，从队头取数据会被阻塞。因为此时还没有数据可取，直到队列中有了数据才能返回；
 * 如果队列已经满了，那么插入数据的操作就会被阻塞，直到队列中有空闲位置后再插入数据，然后再返回。
 *
 * <p>并发队列
 * 线程安全的队列我们叫作并发队列。
 * 最简单直接的实现方式是直接在 enqueue()、dequeue() 方法上加锁，但是锁粒度大并发度会比较低，同一时刻仅允许一个存或者取操作。
 * 实际上，基于数组的循环队列，利用 CAS 原子操作，可以实现非常高效的并发队列。
 *
 * @author penggs
 * @since 2020-08-09 00:06
 */
public class ApCircularQueue<T> {
    private Object[] container;
    private int size = 0;   // 队列当前大小
    private int capacity;   // 队列容量
    private int head = 0;   // 队头下标
    private int tail = 0;   // 队尾下标

    public ApCircularQueue() {
        this.capacity = 11;
        container = new Object[11];
    }

    public ApCircularQueue(int capacity) {
        // 队列容量，由于循环队列会浪费一个数组的存储空间。为了与所以此处加1，保证实际容量与期望容量一致
        this.capacity = capacity + 1;
        container = new Object[capacity + 1];
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
        if ((tail + 1) % capacity == head) {
            return false;
        }
        container[tail] = data;
        tail = (tail + 1) % capacity;
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
            // 队列为空
            return null;
        }
        size--;
        T res = (T) container[head];
        head = (head + 1) % capacity;
        return res;
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
        // 注意比较head和tail的大小，避免数组越界
        if (tail > head) {
            for (int i = head; i < tail; i++) {
                sb.append(container[i].toString()).append(",");
            }
        } else {
            for (int i = head; i < capacity; i++) {
                sb.append(container[i].toString()).append(",");
            }
            for (int i = 0; i < tail; i++) {
                sb.append(container[i].toString()).append(",");
            }
        }
        return sb.toString().substring(0, sb.toString().lastIndexOf(","));
    }

    public static void main(String[] args) {
        ApCircularQueue<String> queue = new ApCircularQueue<>(3);
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