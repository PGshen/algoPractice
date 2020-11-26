package world.playtogether.hot;

import java.util.Arrays;

/**
 * <project> algoPractice
 *
 * <p> 颜色排序
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * @author penggs
 * @since 2020-11-26
 */
public class ApSortColors {
    /**
     * 投机解法
     * @param nums
     */
    public static void sortColors(int[] nums) {
        int n0 = 0, n1 = 0, n2 = 0;
        for (int num : nums) {
            if (num == 0) n0++;
            else if (num == 1) n1++;
            else if (num == 2) n2++;
        }
        int j = 0;
        for(int i = 0; i < n0; i++) {
            nums[j++] = 0;
        }
        for(int i = 0; i < n1; i++) {
            nums[j++] = 1;
        }
        for(int i = 0; i < n2; i++) {
            nums[j++] = 2;
        }
    }

    /**
     * 双指针解法
     * @param nums
     */
    public static void sortColors2(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        // 两个指针分别指向下一个0，2应该在的未知
        int p0 = 0;
        int p2 = nums.length - 1;
        for (int i = p0; i <= p2; i++) {
            // 遇到0进行交换
            if (nums[i] == 0) {
                nums[i] = nums[p0];
                nums[p0++] = 0;
            } else if (nums[i] == 2) {
                nums[i--] = nums[p2];
                nums[p2--] = 2;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,0,2,1,1,0};
        sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }
}