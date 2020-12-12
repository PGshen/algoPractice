package world.playtogether.hot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <project> algoPractice
 *
 * <p> 数组的最长连续序列
 *
 * @author penggs
 * @since 2020-12-12
 */
public class ApLongestConsecutive {
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        // 先排序
        Arrays.sort(nums);

        // max 最终结果, curr 当前长度, last 上个数字
        int max = 1, curr = 1, last = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == last) continue;
            if (nums[i] == last + 1) curr++; // 符合连续，长度 +1
            else {
                max = Math.max(max, curr); // 连不上了，记录长度
                curr = 1; // 重新开始
            }
            last = nums[i];
        }
        max = Math.max(max, curr); // 别忘了最后一段的连续区间
        return max;
    }

    public static int longestConsecutive2(int[] nums) {
        if (nums.length == 0) return 0;
        // 记录当前值v的连续递增的最右边界
        Map<Integer, Integer> map = new HashMap<>(); // 记录区间 [v, r]
        for (int v : nums) map.put(v, v);

        int max = 1;
        for (int v : nums) {
            int r = v;
            while (map.containsKey(r + 1))
                r = map.get(r + 1); // 利用前面已知的右边界，快速找到当前需要的右边界
            map.put(v, r);
            // 肯定能找到最小的值去覆盖
            max = Math.max(max, r - v + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));
        System.out.println(longestConsecutive2(nums));
    }
}