package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 除自身以外的数组乘积
 *
 * @author penggs
 * @since 2021-01-09
 */
public class ApProductExceptSelf {
    /**
     * 乘积 = 当前数左边的乘积 * 当前数右边的乘积
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int p = 1, q = 1;
        // 自身以外左侧的乘积
        for (int i = 0; i < nums.length; i++) {
            res[i] = p;
            p *= nums[i];
        }
        // q 记录除自身以外右侧的乘积
        for (int i = nums.length - 1; i > 0 ; i--) {
            q *= nums[i];
            // 左侧 * 右侧
            res[i - 1] *= q;
        }
        return res;
    }
}