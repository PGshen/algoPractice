package world.playtogether.hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 三数之和
 *
 * @author penggs
 * @since 2020-11-14
 */
public class ApThreeSum {

    /**
     * 暴力迭代解法
     * @param nums 数组
     * @return
     */
    public static List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if (len < 3) return ans;
        // 先排序，方便去重
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break;     //第一个数大于0了，那和肯定大于0，退出循环
            if (i > 0 && nums[i] == nums[i-1]) continue;    // 去重
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j] == nums[j-1]) continue;    // 去重
                for (int k = j + 1; k < len; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return ans;
    }


    /**
     * 固定一个数，然后使用双指针来降低复杂度,从o(n^3)到o(n^2)
     * 注意去重
     * @param nums 数组
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if(len < 3) return ans;
        Arrays.sort(nums); // 排序,是为了方便去重
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4, -1, -1};
        System.out.println(threeSum1(nums).toString());
        System.out.println(threeSum2(nums).toString());
    }
}