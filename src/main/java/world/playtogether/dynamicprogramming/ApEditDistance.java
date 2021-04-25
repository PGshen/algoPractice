package world.playtogether.dynamicprogramming;

/**
 * <project> algoPractice
 *
 * <p> 编辑距离
 *
 * @author penggs
 * @since 2020-08-21
 */
public class ApEditDistance {
    private int minDist = Integer.MAX_VALUE; // 存储结果
    /** 
     * 回溯方式计算莱文斯坦距离
     * 
     * @param a 字符数组a
	 * @param n a长度
	 * @param b 字符数组b
	 * @param m b长度
	 * @param i a索引
	 * @param j b索引
	 * @param edist 当前距离
     * @author penggs
     * @since 2020/8/21 
     */
    public void lwstBT(char[] a, int n, char[] b, int m, int i, int j, int edist) {
        if (i == n || j == m) {
            if (i < n) edist += (n-i);  // b已遍历完
            if (j < m) edist += (m - j);    //a已遍历完
            if (edist < minDist) minDist = edist;   // 对比完成，获取最小的最终距离
            return;
        }
        if (a[i] == b[j]) { // 两个字符匹配
            lwstBT(a, n, b, m,i+1, j+1, edist);
        } else { // 两个字符不匹配
            lwstBT(a, n, b, m,i + 1, j, edist + 1); // 删除a[i]或者b[j]前添加一个字符
            lwstBT(a, n, b, m, i, j + 1, edist + 1); // 删除b[j]或者a[i]前添加一个字符
            lwstBT(a, n, b, m,i + 1, j + 1, edist + 1); // 将a[i]和b[j]替换为相同字符
        }
    }

    public void lwstBT(char[] a, int i, char[] b, int j, int dist) {
        if (i == a.length || j == b.length) {
            if (i < a.length) dist += a.length - i;
            if (j < b.length) dist += b.length - j;
            if (dist < minDist) minDist = dist;
            return;
        }
        if (a[i] == b[j]) {
            // a,b 的两个位置的字符相等，那么都向后推进，距离不需要增加
            lwstBT(a, i + 1, b, j + 1, dist);
        } else {
            lwstBT(a, i + 1, b, j, dist + 1);   // a推进——>删a[i]或者b[j]前面插入一个字符
            lwstBT(a, i, b, j + 1, dist + 1);   // b推进——>删b[j]或者a[i]前面插入一个字符
            lwstBT(a, i + 1, b, j + 1, dist + 1);   // 一起推进——>a[i]或b[j]替换为对方的字符
        }
    }

    /**
     * 状态转移表计算莱文斯坦距离
     *
     * 如果：a[i]!=b[j]，那么：min_edist(i, j)就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1, min_edist(i-1,j-1)+1)
     *
     * 如果：a[i]==b[j]，那么：min_edist(i, j)就等于：
     * min(min_edist(i-1,j)+1, min_edist(i,j-1)+1，min_edist(i-1,j-1))
     *
     *  其中，min表示求三数中的最小值。
     *
     * @param a 字符数组a
     * @param n a长度
     * @param b 字符数组b
     * @param m b长度
     * @return int
     * @author penggs
     * @since 2020/8/21
     */
    public int lwstDP(char[] a, int n, char[] b, int m) {
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; ++j) { // 初始化第0行:a[0..0]与b[0..j]的编辑距离
            if (a[0] == b[j]) minDist[0][j] = j;
            else if (j != 0) minDist[0][j] = minDist[0][j-1]+1;
            else minDist[0][j] = 1;
        }
        for (int i = 0; i < n; ++i) { // 初始化第0列:a[0..i]与b[0..0]的编辑距离
            if (a[i] == b[0]) minDist[i][0] = i;
            else if (i != 0) minDist[i][0] = minDist[i-1][0]+1;
            else minDist[i][0] = 1;
        }
        for (int i = 1; i < n; ++i) { // 按行填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) minDist[i][j] = min(
                        minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]);
                else minDist[i][j] = min(
                        minDist[i-1][j]+1, minDist[i][j-1]+1, minDist[i-1][j-1]+1);
            }
        }
        return minDist[n-1][m-1];
    }

    private int min(int x, int y, int z) {
        int minv = Integer.MAX_VALUE;
        if (x < minv) minv = x;
        if (y < minv) minv = y;
        if (z < minv) minv = z;
        return minv;
    }

    /**
     * 状态转移表计算最长公共子串长度
     *
     * 如果：a[i]==b[j]，那么：max_lcs(i, j)就等于：
     * max(max_lcs(i-1,j-1)+1, max_lcs(i-1, j), max_lcs(i, j-1))；
     *
     * 如果：a[i]!=b[j]，那么：max_lcs(i, j)就等于：
     * max(max_lcs(i-1,j-1), max_lcs(i-1, j), max_lcs(i, j-1))；
     *
     * 其中max表示求三数中的最大值。
     *
     * @param a 字符数组a
     * @param n a长度
     * @param b 字符数组b
     * @param m b长度
     * @return int
     * @author penggs
     * @since 2020/8/21
     */
    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxlcs = new int[n][m];
        for (int j = 0; j < m; ++j) {//初始化第0行：a[0..0]与b[0..j]的maxlcs
            if (a[0] == b[j]) maxlcs[0][j] = 1;
            else if (j != 0) maxlcs[0][j] = maxlcs[0][j-1];
            else maxlcs[0][j] = 0;
        }
        for (int i = 0; i < n; ++i) {//初始化第0列：a[0..i]与b[0..0]的maxlcs
            if (a[i] == b[0]) maxlcs[i][0] = 1;
            else if (i != 0) maxlcs[i][0] = maxlcs[i-1][0];
            else maxlcs[i][0] = 0;
        }
        for (int i = 1; i < n; ++i) { // 填表
            for (int j = 1; j < m; ++j) {
                if (a[i] == b[j]) maxlcs[i][j] = max(
                        maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]+1);
                else maxlcs[i][j] = max(
                        maxlcs[i-1][j], maxlcs[i][j-1], maxlcs[i-1][j-1]);
            }
        }
        return maxlcs[n-1][m-1];
    }

    private int max(int x, int y, int z) {
        int maxv = Integer.MIN_VALUE;
        if (x > maxv) maxv = x;
        if (y > maxv) maxv = y;
        if (z > maxv) maxv = z;
        return maxv;
    }

    public int minDist(char[] a, char[] b) {
        int m = a.length, n = b.length;
        // dp定义为a[0...i] 到 b[0...j]的最小距离
        int[][] dp = new int[m+1][n+1];
        // 初始化
        for (int i = 1; i < m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = j;
        }
        // 填充表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a[i-1] == b[j-1]) {
                    // 相等，直接等于左上的
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // 取左上、左、上中最小的转换到当前值
                    dp[i][j] = 1 + min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        ApEditDistance apEditDistance = new ApEditDistance();
        char[] a = "mitcmu".toCharArray();
        char[] b = "mtacnu".toCharArray();
        int n = 6;
        int m = 6;
        apEditDistance.minDist = Integer.MAX_VALUE;
        apEditDistance.lwstBT(a, n, b, m, 0, 0, 0);
        //apEditDistance.lwstBT(a, 0, b, 0, 0);
        System.out.println(apEditDistance.minDist);
        System.out.println();
        System.out.println(apEditDistance.lwstDP(a, n, b, m));
        System.out.println();
        System.out.println(apEditDistance.lcs(a, n, b, m));
        System.out.println(apEditDistance.minDist(a, b));
    }
}