package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 接雨水
 *
 * @author penggs
 * @since 2020-11-18
 */
public class ApCatchRainwater {
    /**
     * 按列求
     * @param height
     * @return
     */
    public static int catchRainwater(int[] height) {
        int sum = 0;
        int len = height.length;
        if(len == 0) return 0;
        // 左侧最高
        int[] leftMax = new int[len];
        // 右侧最高
        int[] rightMax = new int[len];
        // 分别计算位置i两侧最高的柱子
        leftMax[0] = 0;
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i-1]);
        }
        rightMax[len-1] = 0;
        for (int i = len - 2; i >= 0 ; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i+1]);
        }
        for (int i = 0; i < len; i++) {
            // 大于0表示能接到水
            int catchI = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (catchI > 0) sum += catchI;
        }
        return sum;
    }

    /**
     * 双指针
     * 用两个值分别保存左右两侧的最高
     * @param height
     * @return
     */
    public static int catchRainwater2(int[] height) {
        int sum = 0;
        int max_left = 0;
        int max_right = 0;
        int left = 1;
        int right = height.length - 2; // 加右指针进去
        for (int i = 1; i < height.length - 1; i++) {
            //从左到右更
            if (height[left - 1] < height[right + 1]) {
                max_left = Math.max(max_left, height[left - 1]);
                int min = max_left;
                if (min > height[left]) {
                    sum = sum + (min - height[left]);
                }
                left++;
                //从右到左更
            } else {
                max_right = Math.max(max_right, height[right + 1]);
                int min = max_right;
                if (min > height[right]) {
                    sum = sum + (min - height[right]);
                }
                right--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height2 = new int[]{4,2,0,3,2,5};
        System.out.println(catchRainwater(height));
        System.out.println(catchRainwater(height2));
    }
}