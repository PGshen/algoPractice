package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 乘积最大子数组
 *
 * @author penggs
 * @since 2021-01-03
 */
public class ApMaxProductSubArr {
    public static int maxProduct(int[] nums) {
        // imax 为以nums[i]结尾的最大子数组的乘积
        // imin 为以nums[i]结尾的最小子数组的乘积，记录这个的原因是存在负数时，负负得正，由最小变为最大
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int num: nums) {
            if (num < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(imax * num, num);
            imin = Math.min(imin * num, num);
            max = Math.max(max, imax);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2,3,-2,4};
        System.out.println(maxProduct(arr));
    }
}