package world.playtogether.other;

import java.util.*;

/**
 * <project> algoPractice
 *
 * <p> 两数之和
 * 给定一个数组和一个整数 target，可以保证数组中存在两个数的和为 target，请你返回这两个数的索引
 *
 * @author penggs
 * @since 2020-09-04
 */
public class ApTwoSum {
    /**
     * 暴力法
     * @param nums 数组
     * @param target 目标值
     * @return
     */
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                if (nums[j] == target - nums[i])
                    return new int[] { i, j };
        // 不存在这么两个数
        return new int[] {-1, -1};
    }

    /**
     * 借助哈希表
     * @param nums 数组
     * @param target 目标值
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> index = new HashMap<>();
        // 构造一个哈希表：元素映射到相应的索引,如果有重复值，后面的覆盖前面的
        for (int i = 0; i < n; i++)
            index.put(nums[i], i);

        for (int i = 0; i < n; i++) {
            int other = target - nums[i];
            // 如果 other 存在且不是 nums[i] 本身
            if (index.containsKey(other) && index.get(other) != i)
                return new int[] {i, index.get(other)};
        }
        return new int[] {-1, -1};
    }

    /**
     * 借助哈希表，但拆分添加和查找方法
     * @param nums 数组
     * @param target 目标值
     * @return
     */
    public static int[] twoSum3(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            TwoSum.add(nums[i], i);
        }
        return TwoSum.find(target);
    }

    static class TwoSum {
        static Map<Integer, List<Integer>> freq = new HashMap<>();

        public static void add(int number, int index) {
            // 记录 number 出现的位置
            List<Integer> indexs = freq.getOrDefault(number, new ArrayList<>());
            indexs.add(index);
            freq.put(number, indexs);
        }

        public static int[] find(int value) {
            for (Integer key : freq.keySet()) {
                int other = value - key;
                List<Integer> indexs = freq.getOrDefault(key, new ArrayList<>());
                // 情况一
                if (other == key && indexs.size() > 1)
                    return new int[]{indexs.get(0), indexs.get(1)};
                // 情况二
                if (other != key && freq.containsKey(other))
                    return new int[]{freq.get(key).get(0), freq.get(other).get(0)};
            }
            return new int[]{-1, -1};
        }
    }

    /**
     * 数组有序的情况下，使用双指针
     * @param nums 数组
     * @param target 目标值
     * @return
     */
    public static int[] twoSum4(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++; // 让 sum 大一点
            } else if (sum > target) {
                right--; // 让 sum 小一点
            }
        }
        // 不存在这样两个数
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 3, 5, 6, 3, 7, 3, 10};
        System.out.println(Arrays.toString(twoSum1(arr, 9)));
        System.out.println(Arrays.toString(twoSum2(arr, 6)));
        System.out.println(Arrays.toString(twoSum3(arr, 13)));

        int[] arr2 = new int[] {2, 3, 5, 6, 7, 10};
        System.out.println(Arrays.toString(twoSum4(arr2, 13)));
    }
}