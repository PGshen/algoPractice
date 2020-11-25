package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 跳跃游戏
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个位置。
 *
 * @author penggs
 * @since 2020-11-25
 */
public class ApCanJump {
    /**
     * 贪心解法，求解能跳最远的距离，如果最远距离大于数组长度，那么可以到达。因为如果能到这个点，那么就能到达前面的所有点
     * @param arr
     * @return
     */
    public static boolean canJump(int[] arr) {
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            // 能达到i点，并且基于这个点还可以跳更远
            if (maxLen >= i && i + arr[i] > maxLen) {
                maxLen = i + arr[i];
            }
        }
        return maxLen >= arr.length - 1;
    }

    /**
     * 回溯解法
     * @param arr
     * @return
     */
    public static boolean canJump2(int[] arr) {
        return backtrack(arr, 0, arr.length - 1);
    }

    /**
     * 回溯
     * @param arr
     * @param pos 表示当前的位置
     * @param target 目标距离
     * @return
     */
    private static boolean backtrack(int[] arr, int pos, int target) {
        if (target == 0) return true;
        if (target < 0) return false;
        if (pos >= arr.length) return false;
        // 选择,迭代每个能走的步长
        for (int i = 1; i <= arr[pos]; i++) {
            // 递归，pos+i表示到达第pos+i个位置
            if (backtrack(arr, pos+i, target - i)) return true;
        }
        // 撤销选择，因为传递的是值，方法出栈后相当于撤销了选择
        // 最终依然
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2,3,1,1,4};
        int[] arr2 = new int[] {3,2,1,0,4};
        System.out.println(canJump2(arr));
        System.out.println(canJump2(arr2));
    }
}