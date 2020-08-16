package world.playtogether.heap;

import java.lang.reflect.Array;

/**
 * <project> algoPractice
 *
 * <p> 堆-求topk
 *
 * @author penggs
 * @since 2020-08-15
 */
@SuppressWarnings("unchecked")
public class ApTopkCount<T extends Comparable<T>> {
    /**
     * topK
     *
     * @param data 数据
	 * @param tClass 泛型class
	 * @param k k
     * @return T[] topK数组
     * @author penggs
     * @since 2020/8/15
     */
    public T[] topK(T[] data, Class<T> tClass, int k) {
        // 通过一个小顶堆实现一个优先级队列
        ApHeap<T> priorityQueue = new ApHeap<T>(false);

        for (T datum : data) {
            // 未满，直接入队
            if (priorityQueue.getCount() < k) {
                priorityQueue.insert(datum);
            } else {
                // 已满，同队头比较，若比队头大，先出队，然后新值入队
                T topData = priorityQueue.getTop();
                if (datum.compareTo(topData) > 0) {
                    priorityQueue.removeTop();
                    priorityQueue.insert(datum);
                }
            }
        }
        // 通过反射的方式创建泛型数组，需要传入class类
        T[] result = (T[]) Array.newInstance(tClass, k);
        int index = 0;
        while (priorityQueue.getCount() > 0) {
            result[index++] = priorityQueue.removeTop();
        }
        return result;
    }

    public static void main(String[] args) {
        ApTopkCount<Integer> apTopkCount = new ApTopkCount<>();
        Integer[] source = new Integer[] {2, 3, 4, 66, 77, 1, 334, 567};
        Integer[] res = apTopkCount.topK(source, Integer.class, 3);
        for (Integer re : res) {
            System.out.print(re + " ");
        }
    }
}