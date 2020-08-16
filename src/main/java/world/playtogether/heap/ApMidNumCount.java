package world.playtogether.heap;

/**
 * <project> algoPractice
 *
 * <p> 堆-求中位数
 *
 * @author penggs
 * @since 2020-08-15
 */
public class ApMidNumCount<T extends Comparable<T>> {

    // 大顶堆，存储前半部分
    private ApHeap<T> bigHeap = new ApHeap<>(51);
    // 小顶堆，存储后半部分
    private ApHeap<T> littleHeap = new ApHeap<>(51, false);
    private int count;

    /**
     * 插入数据
     *
     * @param data 值
     * @author penggs
     * @since 2020/8/15
     */
    public void putData(T data) {
        count++;
        // 如果为空，那就插入大堆顶
        if (bigHeap.isEmpty() && littleHeap.isEmpty()) {
            bigHeap.insert(data);
            return;
        }
        // 比大顶堆顶大，那插入小顶堆
        if (bigHeap.getTop().compareTo(data) < 0) {
            littleHeap.insert(data);
        } else {
            // 否则插入大顶堆
            bigHeap.insert(data);
        }
        int midCount = count / 2;
        // 超过了一半，移动到另外一个
        if (bigHeap.getCount() > midCount) {
            moveData(bigHeap, littleHeap, bigHeap.getCount() - midCount);
            return;
        }
        if (littleHeap.getCount() > midCount) {
            moveData(littleHeap, bigHeap, littleHeap.getCount() - midCount);
        }
    }

    /**
     * 获取中位数，即大顶堆的堆顶
     *
     * @return T
     * @author penggs
     * @since 2020/8/15
     */
    public T getMidNum() {
        // 偶数时返回前面的那个
        return bigHeap.getTop();
    }

    /**
     * 将数据从一个堆搬移到另一个堆
     *
     * @param src 源堆
	 * @param dst 目的堆
	 * @param moveNum 移动个数
     * @author penggs
     * @since 2020/8/15
     */
    private void moveData(ApHeap<T> src, ApHeap<T> dst, int moveNum) {
        for (int i = 0; i < moveNum; i++) {
            dst.insert(src.removeTop());
        }
    }

    public static void main(String[] args) {
        ApMidNumCount<Integer> midNumCount = new ApMidNumCount<>();
        midNumCount.putData(222);
        midNumCount.putData(2222);
        midNumCount.putData(2223);
        midNumCount.putData(2224);
        midNumCount.putData(22);
        midNumCount.putData(11);
        midNumCount.putData(1);
        midNumCount.putData(3);
        midNumCount.putData(33);
        midNumCount.putData(55);
        midNumCount.putData(99);
        midNumCount.putData(77);
        midNumCount.putData(88);
        System.out.print(midNumCount.getMidNum());
    }
}