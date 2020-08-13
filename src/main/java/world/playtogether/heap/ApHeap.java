package world.playtogether.heap;

/**
 * <project> algoPractice
 *
 * <p> 堆
 *
 * @author penggs
 * @since 2020-08-13
 */
@SuppressWarnings("unchecked")
public class ApHeap<T extends Comparable<T>> {
    private Object[] container;
    private int capacity;
    private int count;

    public ApHeap() {
        this.container = new Object[10];
        this.capacity = 10;
        this.count = 0;
    }

    public ApHeap(int capacity) {
        this.container = new Object[capacity];
        this.capacity = capacity;
        this.count = 0;
    }

    /**
     * 插入值（大顶堆）
     *
     * @param data 值
     * @author penggs
     * @since 2020/8/13
     */
    public void insert(T data) {
        if (count > capacity) {
            return;
        }
        container[count++] = data;
        int i = count - 1;
        // 自下向上堆化
        while (i/2 >=0 && ((T)container[i]).compareTo((T)container[(i-1)/2]) > 0) {
            swap(container, i, (i-1)/2);
            i = (i-1)/2;
        }
    }

    /**
     * 移除堆顶
     *
     * @author penggs
     * @since 2020/8/13
     */
    public void removeMax() {
        if (count == 0) return;
        container[0] = container[count-1];
        heapify(container, count, 0);
        count--;
    }

    /**
     * 从非叶子节点开始堆化，构建堆
     *
     * @param container
	 * @param len
     * @author penggs
     * @since 2020/8/13
     */
    public void buildHeap(Object[] container, int len) {
        // 从非叶子节点开始堆化
        for (int i = len/2; i >=0; i--) {
            heapify(container, len, i);
        }
    }

    /**
     * 每次纳入一个值，进行堆化，构建堆
     *
     * @param container
	 * @param len
     * @author penggs
     * @since 2020/8/13
     */
    public void buildHeap2(Object[] container, int len) {
        for (int i = 0; i < len; i++) {
            int pi = i;
            // 建container[pi]纳入堆，然后进行堆化
            while (pi/2 >=0 && ((T)container[pi]).compareTo((T)container[(pi-1)/2]) > 0) {
                swap(container, pi, (pi-1)/2);
                pi = (pi-1)/2;
            }
        }
    }

    /**
     * 自上向下堆化
     *
     * @param container 容器
	 * @param len 当前长度
	 * @param i 堆化的节点下标
     * @author penggs
     * @since 2020/8/13
     */
    private void heapify(Object[] container, int len, int i) {
        while (true) {
            int maxPos = i;
            // 左节点
            if (i * 2 + 1 < len && ((T)container[maxPos]).compareTo((T)container[i*2+1]) < 0) {
                maxPos = i * 2 + 1;
            }
            // 右节点
            if (i * 2 + 2 < len && ((T)container[maxPos]).compareTo((T)container[i*2+2]) < 0) {
                maxPos = i * 2 + 2;
            }
            if (maxPos == i) {
                break;
            }
            swap(container, i, maxPos);
            i = maxPos;
        }
    }

    public void swap(Object[] container, int i, int j) {
        Object temp = container[i];
        container[i] = container[j];
        container[j] = temp;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(container[i].toString()).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        ApHeap<Integer> apHeap = new ApHeap<>();
        apHeap.insert(11);
        apHeap.insert(2);
        apHeap.insert(3);
        apHeap.insert(4);
        apHeap.insert(15);
        System.out.println(apHeap);
        apHeap.removeMax();
        System.out.println(apHeap);
        Integer[] ints = new Integer[]{22,3,4,5,77,53};
        apHeap.buildHeap2(ints, 6);
        for (Integer anInt : ints) {
            System.out.print(anInt + " ");
        }
    }
}