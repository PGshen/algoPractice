package world.playtogether.hot;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 多少元素
 */
public class ApMajorityElement {
    /**
     * 用map保存计数
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums){
        Map<Integer, Long> count = Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.<Integer>counting()));
        long limit = nums.length >> 1;
        for (Map.Entry<Integer, Long> entry: count.entrySet()){
            if (entry.getValue() > limit)
                return entry.getKey();
        }
        return -1;
    }

    /**
     * 摩尔投票法
     * 相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少1个“多数元素”。
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        // 第一次以第一个作为候选人
        int candNum = nums[0], count = 1;
        for (int i = 1; i < nums.length; ++i) {
            if (candNum == nums[i])
                ++count;
            else if (--count == 0) {
                candNum = nums[i];
                count = 1;
            }
        }
        return candNum;
    }

}
