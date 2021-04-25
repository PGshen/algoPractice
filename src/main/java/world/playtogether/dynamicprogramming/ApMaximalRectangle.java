package world.playtogether.dynamicprogramming;

/**
 * <project> algoPractice
 *
 * <p> 最大矩形
 *
 * @author penggs
 * @since 2021-04-02
 */
public class ApMaximalRectangle {
    public static int maximalRectangle(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        // 按行计算heights
        for (int row = 0; row < matrix.length; row++) {
            //遍历每一列，更新高度
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == 1) {
                    heights[col] += 1;
                } else {
                    heights[col] = 0;
                }
            }
            //调用上一题的解法，更新函数
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    public static int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            // 以heights[i]为中心，找左侧大于等于当前高度的下标
            int maxLeft = i;
            while (maxLeft >= 0 && heights[maxLeft] >= heights[i]) {
                maxLeft--;
            }
            // 以heights[i]为中心，找右侧大于等于当前高度的下标
            int maxRight = i;
            while (maxRight < heights.length && heights[maxRight] >= heights[i]) {
                maxRight++;
            }
            // 判断是否能超过当前最大值
            maxArea = Math.max(maxArea, heights[i] * (maxRight - maxLeft - 1));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};
        System.out.println(maximalRectangle(matrix));
    }
}