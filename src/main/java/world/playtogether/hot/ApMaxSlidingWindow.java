package world.playtogether.hot;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <project> algoPractice
 *
 * <p> 滑动窗口内的最大值
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 *
 * @author penggs
 * @since 2021-01-10
 */
public class ApMaxSlidingWindow {
    Deque<Integer> deque = new LinkedList<>();
    public int[] maxSlidingWindow(int[] nums, int k) {
        Integer[] res = new Integer[nums.length];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                push(nums[i]);
            } else {
                push(nums[i]);
                res[j++] = max();
                pop(nums[i - k + 1]);
            }
        }
        int[] ress = new int[j];
        for(int m = 0; m < j; m++) {
            ress[m] = res[m];
        }
        return ress;
    }

    /**
     * 插入队列，将比t小的值，出队列，知道遇到比t大的
     * @param t
     */
    public void push(Integer t) {
        while (!deque.isEmpty() && deque.getLast().compareTo(t) < 0) {
            deque.removeLast();
        }
        deque.addLast(t);
    }

    /**
     * 获取队列最大值，队头即为最大值
     * @return
     */
    public Integer max() {
        return deque.getFirst();
    }

    /**
     * 移除队头
     * @param t
     */
    public void pop(Integer t) {
        // 判断是否等于t，来确定是否可以能移除对头，因为队列可能由于push操作移除数据后导致没达到窗口值
        if (!deque.isEmpty() && deque.getFirst().equals(t)){
            deque.removeFirst();
        }
    }
}