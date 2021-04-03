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
        int ret = 0;
        int xLen = matrix.length;
        int yLen = matrix[0].length;
        int[][] maxMatrix = new int[xLen][yLen];
        // 初始化
        for (int i = 0; i < xLen; i++) {
            maxMatrix[i][0] = matrix[i][0];
        }
        for (int j = 0; j < yLen; j++) {
            maxMatrix[0][j] = matrix[0][j];
        }
        for (int i = 1; i < xLen; i++) {
            for (int j = 1; j < yLen; j++) {
                if (matrix[i][j] == 0) continue;
                int curVal = 0;
                if (maxMatrix[i][j-1] == 0 || maxMatrix[i-1][j] == 0) {
                    curVal = Math.max(maxMatrix[i][j-1], maxMatrix[i-1][j]) + 1;
                } else {
                    curVal = maxMatrix[i][j-1] + maxMatrix[i-1][j] - maxMatrix[i-1][j-1] + 1;
                }
                maxMatrix[i][j] = curVal;
                if (curVal > ret) ret = curVal;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,0,1,0,0},{1,0,1,1,1},{1,1,1,1,1},{1,0,0,1,0}};
        System.out.println(maximalRectangle(matrix));
    }
}