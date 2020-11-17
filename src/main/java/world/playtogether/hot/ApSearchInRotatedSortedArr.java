package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 旋转的有序数组查找值
 *
 * @author penggs
 * @since 2020-11-17
 */
public class ApSearchInRotatedSortedArr {
    /**
     * 二分法搜索，注意范围判断和边界条件
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (nums[mid] == target) return mid;
            // 分为两部分后一定有一侧是完全有序的，通过中点数判断有序是在左还是在右侧
            if (nums[mid] >= nums[low]) {
                // 有序在左侧
                if (nums[mid] > target && target >= nums[low]) {
                    // 在左侧的中点的左侧
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // 有序在右侧
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,6,7,0,1,2};
        System.out.println(search(nums, 5));
        System.out.println(search(nums, 1));
        System.out.println(search(nums, 4));
        System.out.println(search(nums, 2));
        System.out.println(search(nums, 7));
    }
}