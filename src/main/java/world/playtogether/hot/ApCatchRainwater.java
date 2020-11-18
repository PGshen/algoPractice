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

    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height2 = new int[]{4,2,0,3,2,5};
        System.out.println(catchRainwater(height));
        System.out.println(catchRainwater(height2));
    }
}