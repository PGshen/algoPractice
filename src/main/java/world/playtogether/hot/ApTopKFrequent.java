package world.playtogether.hot;

import java.util.*;

/**
 * <project> algoPractice
 *
 * <p> 频率topk
 *
 * @author penggs
 * @since 2021-01-13
 */
public class ApTopKFrequent {
    /**
     * Map统计频率，用堆保存topK
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 统计频率
        Map<Integer, Integer> count = new HashMap<>();
        for (int num: nums) {
            count.compute(num, (key, val) -> val == null ? 1 : val + 1);
        }
        // 存入key,根据val频率排序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(count::get));
        for (Integer key: count.keySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(key);
            } else if (count.get(priorityQueue.peek()) < count.get(key)) {
                // 超过堆限制大小并且堆顶元素对应的频率小于要加入的，先弹出再入新值
                priorityQueue.poll();
                priorityQueue.offer(key);
            }
        }
        // 全部提出
        int i = 0;
        int[] ret = new int[k];
        while (!priorityQueue.isEmpty()) {
            ret[i++] = priorityQueue.poll();
        }
        return ret;
    }

    /**
     * 用桶排序
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        // 统计频率
        Map<Integer, Integer> count = new HashMap<>();
        for (int num: nums) {
            count.compute(num, (key, val) -> val == null ? 1 : val + 1);
        }
        // 频率作为数组下标，值作为当前频率对应的全部key
        List<Integer>[] bucket = new List[nums.length + 1];
        for (Integer key: count.keySet()) {
            int fre = count.get(key);
            if (bucket[fre] == null) {
                bucket[fre] = new ArrayList<>();
            }
            bucket[fre].add(key);
        }
        // 结果集
        List<Integer> ret = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && ret.size() < k ; i--) {
            if (bucket[i] == null) continue;
            ret.addAll(bucket[i]);
        }
        return ret.stream().mapToInt(Integer::valueOf).toArray();
    }
}