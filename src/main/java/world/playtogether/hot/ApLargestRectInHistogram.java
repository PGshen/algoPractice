package world.playtogether.hot;

import java.util.Stack;

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

    // 利用单调栈的方式
    public int largestRectangleArea2(int[] heights) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        int[] new_heights = new int[heights.length + 2];
        System.arraycopy(heights, 0, new_heights, 1, heights.length + 1 - 1);
        for (int i = 0; i < new_heights.length; i++) {
            // 一直出栈，直到栈顶元素比新元素小，保证单调（这个过程相当于往回看，就能获取到对应柱子的宽度，然后计算面积）
            while (!stack.isEmpty() && new_heights[stack.peek()] > new_heights[i]) {
                int cur = stack.pop();
                int l = stack.peek();   // 左边界
                int r = i;              // 右边界
                res = Math.max(res, (r - l - 1) * new_heights[cur]);
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }
}