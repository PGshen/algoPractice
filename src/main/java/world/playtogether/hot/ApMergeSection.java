package world.playtogether.hot;

import java.util.Arrays;

/**
 * <project> algoPractice
 *
 * <p> 合并区间
 *
 * @author penggs
 * @since 2020-11-25
 */
public class ApMergeSection {
    public static int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if(len < 1) return intervals;
        // 先排序
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        int index = 0;
        int[][] res = new int[len][2];
        res[0] = intervals[0];
        for(int i = 1; i < len; i++) {
            int[] cur = intervals[i];
            // 当前区间与res的最后一个区间是否有交集
            if (cur[0] <= res[index][1]) {
                // 有交集，延长
                res[index][1] = Math.max(res[index][1], cur[1]);
            } else {
                // 无交集，res新增一个区间
                res[++index] = cur;
            }
        }
        // 返回结果,注意要截断多余的
        return Arrays.copyOf(res, index + 1);
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        System.out.println(Arrays.deepToString(merge(intervals)));
    }

}