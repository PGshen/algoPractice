package world.playtogether.other;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * <project> algoPractice
 *
 * <p> 区间问题
 *
 * @author penggs
 * @since 2020-09-09
 */
public class ApInterval {
    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     * @param intervals 区间
     * @return 合并后的区间
     */
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
        // 返回结果
        return Arrays.copyOf(res, index + 1);
    }

    /**
     * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
     * 返回这两个区间列表的交集。
     * @param A 区间A
     * @param B 区间B
     * @return 区间交集
     */
    public static int[][] intersect(int[][] A, int[][] B) {
        int i = 0, j = 0;
        int lenA = A.length, lenB = B.length;
        int[][] res = new int[lenA + lenB][2];
        int k = 0;
        // 双指针，分别移动
        while (i < lenA && j < lenB) {
            int a1 = A[i][0], a2 = A[i][1];
            int b1 = B[j][0], b2 = B[j][1];
            // 有交集的情况
            if (b2 >= a1 && a2 >= b1) {
                // 取交集，画出图分析就OK
                res[k++] = new int[] {Math.max(a1, b1), Math.min(a2, b2)};
            }
            // 根据大小决定移动哪个指针
            if (a2 > b2) j++; else i++;
        }
        return Arrays.copyOf(res, k);
    }

    /**
     * 给出很多形如[start,end]的闭区间，设计一个算法，算出这些区间中最多有几个互不相交的区间。
     * @param intervals 区间
     * @return 个数
     */
    public static int noOverlapNum(int[][] intervals) {
        if (intervals.length < 1) return 0;
        // [start, end] 按end进行排序
        Arrays.sort(intervals, Comparator.comparingInt(x -> x[1]));
        int count = 1;
        int end = intervals[0][1];
        for (int[] cur: intervals) {
            if (cur[0] >= end) {
                count++;
                end = cur[1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] intervals = new int[][] {{1,3}, {2,6}, {8,10}, {15,18}};
        Arrays.asList(merge(intervals)).forEach(x -> Collections.singletonList(x).forEach(y -> {System.out.print(y[0]); System.out.print(" "); System.out.println(y[1]);}));
        System.out.println();
        int[][] A = new int[][] {{0,2}, {5,10}, {13,23}, {24,25}};
        int[][] B = new int[][] {{1,5}, {8,12}, {15,24}, {25,26}};
        Arrays.asList(intersect(A, B)).forEach(x -> Collections.singletonList(x).forEach(y -> {System.out.print(y[0]); System.out.print(" "); System.out.println(y[1]);}));
        System.out.println();
        System.out.println(noOverlapNum(intervals));
    }
}