package world.playtogether.list;

import java.util.HashMap;
import java.util.Map;

/**
 * <project> algoPractice
 *
 * <p> 基于数组实现LRU
 * 思路: 维护一个有序数组，越靠近数组尾部的结点是越早之前访问的。当有一个新的数据被访问时，我们从数组头开始顺序遍历数组。
 * 1. 如果此数据之前已经被缓存在数组中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到数组的头部。
 * 2. 如果此数据没有在缓存数组中，又可以分为两种情况：
 *      如果此时缓存未满，则将此结点直接插入到数组的头部；
 *      如果此时缓存已满，则数组最后一位删除，将新的数据插入数组的头部。
 * 因为不管缓存有没有满，我们都需要遍历一遍，所以这种实现思路，缓存访问的时间复杂度为 O(n)。实际上，我们可以继续优化这个实现思路，比如引入散列表（Hash table）来记录每个数据的位置，将缓存访问的时间复杂度降到 O(1)。
 *
 * @author penggs
 * @since 2020-08-08 09:17
 */
public class ApLruBasedArray<T> {
    private final ApArrayList<T> container;

    // 直接借助数组的容量来作为缓存的容量

    public ApLruBasedArray() {
        container = new ApArrayList<>();
    }

    public ApLruBasedArray(int capacity) {
        container = new ApArrayList<>(capacity);
    }

    public void offer(T data) {
        int index = container.find(data);
        if (index > -1) {
            // 若已存在，删除节点
            container.remove(index);
        } else {
            // 缓存已满，删除尾节点
            if (container.isFull()) {
                container.removeLast();
            }
        }
        // 将值写入头部
        container.addFirst(data);
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
        ApLruBasedArray<String> lru = new ApLruBasedArray<>(3);
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