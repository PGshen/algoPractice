package world.playtogether.queue;

import java.util.*;

/**
 * <project> algoPractice
 *
 * <p> 单调队列,基于双端队列实现
 *
 * @author penggs
 * @since 2020-09-20
 */
public class ApMonotonicQueue<T extends Comparable<T>> {
    Deque<T> deque = new LinkedList<>();

    /**
     * 插入队列，将比t小的值，出队列，知道遇到比t大的
     * @param t
     */
    public void push(T t) {
        while (!deque.isEmpty() && deque.getLast().compareTo(t) < 0) {
            deque.removeLast();
        }
        deque.addLast(t);
    }

    /**
     * 获取队列最大值，队头即为最大值
     * @return
     */
    public T max() {
        return deque.getFirst();
    }

    /**
     * 移除队头
     * @param t
     */
    public void pop(T t) {
        // 判断是否等于t，来确定是否可以能移除对头，因为队列可能由于push操作移除数据后导致没达到窗口值
        if (!deque.isEmpty() && deque.getFirst().equals(t)){
            deque.removeFirst();
        }
    }

    /**
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * @param list
     * @param k
     * @return 返回滑动窗口中的最大值
     */
    public List<T> maxSlidingWin(List<T> list, int k) {
        List<T> res = new ArrayList<>();
        ApMonotonicQueue<T> window = new ApMonotonicQueue<>();
        for (int i = 0; i < list.size(); i++) {
            if (i < k - 1) {
                window.push(list.get(i));
            } else {
                window.push(list.get(i));
                res.add(window.max());
                window.pop(list.get(i - k + 1));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ApMonotonicQueue<Integer> queue = new ApMonotonicQueue<>();
        List<Integer> list = Arrays.asList(1, 3, -1, -3, 5, 3, 6, 7);
        int k = 3;
        queue.maxSlidingWin(list, k).forEach(System.out::println);
    }
}