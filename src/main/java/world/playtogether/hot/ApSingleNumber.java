package world.playtogether.hot;

import java.util.HashSet;
import java.util.Set;

/**
 * <project> algoPractice
 *
 * <p> 只出现一次的数
 *
 * @author penggs
 * @since 2020-12-12
 */
public class ApSingleNumber {
    /**
     * 利用异或运算特性,重复的值会被消除
     * x ^ 0 = x
     * x & x = 0
     * x ^ y ^ x = x ^ x ^ y = 0 ^ y = y
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int ans = 0;
        for(int num: nums) {
            ans ^= num;
        }
        return ans;
    }

    /**
     * 利用set的不重复
     * @param nums 数组
     * @return
     */
    public static int singleNumber2(int[] nums) {
        int len = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            // 写不进去说明已存在，那么把已存在的移除掉
            if (!set.add(num)) {
                set.remove(num);
            }
        }
        return set.iterator().next();
    }
}