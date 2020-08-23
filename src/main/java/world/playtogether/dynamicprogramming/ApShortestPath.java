package world.playtogether.dynamicprogramming;

/**
 * <project> algoPractice
 *
 * <p> 最短路径
 * 假设我们有一个 n 乘以 n 的矩阵 w[n][n]。矩阵存储的都是正整数。
 * 棋子起始位置在左上角,终止位置在右下角。我们将棋子从左上角移动到右下角。
 * 每次只能向右或者向下移动一位。从左上角到右下角,会有很多不同的路径可以走。
 * 我们把每条路径经过的数字加起来看作路径的长度。那从左上角移动到右下角的最短路径长度是多少呢？
 * <p>
 * min_dist(i, j) = w[i][j] + min(min_dist(i, j-1), min_dist(i-1, j))
 *
 * @author penggs
 * @since 2020-08-20
 */
public class ApShortestPath {

    private int minDist = Integer.MAX_VALUE; // 全局变量或者成员变量
    /**
     * 回溯算法
     *
     * @param i    行
     * @param j    列
     * @param dist 当前路径长度
     * @param w    矩阵
     * @param n    矩阵大小
     * @author penggs
     * @since 2020/8/20
     */
    // 调用方式：minDistBacktracing(0, 0, 0, w, n);
    public void minDistBT(int i, int j, int dist, int[][] w, int n) {
        // 到达了n-1, n-1这个位置了,这里看着有点奇怪哈,你自己举个例子看下
        if (i == n - 1 && j == n - 1) {
            if (dist < minDist) minDist = dist;
            return;
        }
        if (i < n - 1) { // 往下走,更新i=i+1, j=j
            minDistBT(i + 1, j, dist+w[i][j], w, n);
        }
        if (j < n - 1) { // 往右走,更新i=i, j=j+1
            minDistBT(i, j+1, dist+w[i][j], w, n);
        }
    }

    /**
     * 动态规划,状态转移表法
     *
     * @param matrix 矩阵
     * @param n      矩阵大小
     * @return int
     * @author penggs
     * @since 2020/8/20
     */
    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; ++j) { // 初始化states的第一行数据
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { // 初始化states的第一列数据
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
            }
        }
        return states[n - 1][n - 1];
    }

    // 备忘录
    private int[][] mem = new int[4][4];

    /**
     * 动态规划,状态转移方程,备忘录方式
     *
     * @param matrix 矩阵
     * @param i      行
     * @param j      列
     * @return int
     * @author penggs
     * @since 2020/8/20
     */
    public int minDistFunc(int[][] matrix, int i, int j) { // 调用minDist(n-1, n-1);
        if (i == 0 && j == 0) return matrix[0][0];
        if (mem[i][j] > 0) return mem[i][j];
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDistFunc(matrix, i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDistFunc(matrix, i - 1, j);
        }

        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

    public static void main(String[] args) {
        ApShortestPath apShortestPath = new ApShortestPath();
        // 回溯方式
        int[][] matrix1 = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
        apShortestPath.minDistBT(0, 0, 0, matrix1, 4);
        System.out.println(apShortestPath.minDist);
        // 状态转换表
        int[][] matrix2 = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
        System.out.println(apShortestPath.minDistDP(matrix2, 4));
        // 状态转移方程
        int[][] matrix3 = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
        System.out.println(apShortestPath.minDistDP(matrix3, 4));
    }
}