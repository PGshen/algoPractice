package world.playtogether.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <project> algoPractice
 * 双指针用法
 * <p> 三数之和
 *
 * @author penggs
 * @since 2020-09-05
 */
public class ApThreeSum {
    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 指定值 ？请你找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        if(len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            if(nums[i] > target) break; // 如果当前数字大于target，则三数之和一定大于target，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int left = i+1;
            int right = len-1;
            // 从索引为0开始，固定一个值，在后续数组中使用双指针移动查找合适的
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == target){
                    // 合法的
                    ans.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    // 找到一个合法的，继续移动，可能还有合法的
                    while (left<right && nums[left] == nums[left+1]) left++; // 去重
                    while (left<right && nums[right] == nums[right-1]) right--; // 去重
                    left++;
                    right--;
                }
                // 太小了，左指针右移
                else if (sum < target) left++;
                // 太大了，右指针左移
                else if (sum > target) right--;
            }
        }
        return ans;
    }

    /**
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for(int i=0;i<nums.length;i++) {
            int left = i+1, right = nums.length - 1;
            while(left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                // 新的值更接近，替换
                if(Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                // 调整左右指针
                if(sum > target)
                    right--;
                else if(sum < target)
                    left++;
                else
                    // 刚好等于
                    return ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-1, 0, 1, 2, -1, -4};
        threeSum(arr, 3).forEach(System.out::println);
        System.out.println(threeSumClosest(arr, 4));
    }
}