package world.playtogether.heap;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * <project> algoPractice
 *
 * <p> 第n个丑数
 *
 * @author penggs
 * @since 2021-04-08 13:41
 */
public class ApNthUglyNumber {


    public int nthUglyNumber(int n) {
        long res = 1;
        // 小顶堆
        PriorityQueue<Long> heap = new PriorityQueue<>(n + 1, Long::compareTo);
        Set<Long> inHeap = new HashSet<>(); // 用于判断是否已加入堆，来去重
        for (int i = 1; i < n; i++) {
            if (!inHeap.contains(res*2)) {
                heap.offer(res*2);
                inHeap.add(res*2);
            }
            if (!inHeap.contains(res*3)) {
                heap.offer(res*3);
                inHeap.add(res*3);
            }
            if (!inHeap.contains(res*5)) {
                heap.offer(res*5);
                inHeap.add(res*5);
            }
            res = heap.poll();
        }
        return Math.toIntExact(res);
    }



    // 判断是否丑数
    public boolean isUgly(int n) {
        if(n==0) return false;
        if(n==1) return true;
        if(n % 2 == 0) {
            return isUgly(n/2);
        }
        if(n % 3 == 0) {
            return isUgly(n/3);
        }
        if(n % 5 == 0) {
            return isUgly(n/5);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1399680000);
        System.out.println(new ApNthUglyNumber().nthUglyNumber(1600));
    }
}