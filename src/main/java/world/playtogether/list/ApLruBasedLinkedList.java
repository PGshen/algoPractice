package world.playtogether.list;

/**
 * <project> algoPractice
 *
 * <p> 基于链表实现LRU
 * 思路：同基于数组实现的LRU一样
 *
 * @author penggs
 * @since 2020-08-08 09:18
 */
public class ApLruBasedLinkedList<T> {
    private final ApSingleLinkedList<T> container;
    // 容量
    private int capacity = 10;

    public ApLruBasedLinkedList() {
        this.container = new ApSingleLinkedList<>();
    }

    public ApLruBasedLinkedList(int capacity) {
        this.container = new ApSingleLinkedList<>();
        this.capacity = capacity;
    }

    public void offer(T data) {
        ApSingleLinkedList.Node<T> node = container.findByData(data);
        if (node != null) {
            container.deleteByNode(node);
        } else {
            if (container.getSize() == capacity) {
                // 删除尾节点
                container.deleteTail();
            }
        }
        // 将数据插入到头节点
        container.insertToHead(data);
    }

    @Override
    public String toString() {
        return container.toString();
    }

    /**
     * 测试
     *
     * @param args args
     * @author penggs
     * @since 2020/8/8
     */
    public static void main(String[] args) {
        ApLruBasedLinkedList<String> lru = new ApLruBasedLinkedList<>(3);
        lru.offer("One");
        lru.offer("Two");
        lru.offer("Three");
        System.out.println(lru.toString());
        lru.offer("One");
        System.out.println(lru.toString());
        lru.offer("Four");
        System.out.println(lru.toString());
        lru.offer("Five");
        System.out.println(lru.toString());
    }
}