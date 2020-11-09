package world.playtogether.hot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <project> algoPractice
 *
 * <p> 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标
 *
 * @author penggs
 * @since 2020-11-08
 */
public class ApTwoSum {
    /**
     * 借助map缓存，时间复杂度o(n)
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 暴力解法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 4, 6, 3, 9, 20};
        System.out.println(Arrays.toString(twoSum(arr, 29)));
        System.out.println(Arrays.toString(twoSum2(arr, 29)));
    }
}