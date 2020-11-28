package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 柱状图中的最大矩形面积
 *
 * @author penggs
 * @since 2020-11-28
 */
public class ApLargestRectInHistogram {
    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            // 以heights[i]为中心，找左侧大于等于当前高度的下标
            int maxLeft = i;
            while (maxLeft > 0 && heights[maxLeft-1] >= heights[i]) {
                maxLeft--;
            }
            // 以heights[i]为中心，找右侧大于等于当前高度的下标
            int maxRight = i;
            while (maxRight < heights.length - 1 && heights[maxRight+1] >= heights[i]) {
                maxRight++;
            }
            // 判断是否能超过当前最大值
            maxArea = Math.max(maxArea, heights[i] * (maxRight - maxLeft + 1));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }
}