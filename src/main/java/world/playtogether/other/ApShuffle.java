package world.playtogether.other;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <project> algoPractice
 *
 * <p> 洗牌/随机乱置算法
 *
 * @author penggs
 * @since 2020-09-10
 */
public class ApShuffle {
    int N = 10000;
    Map<String, Integer> count = new HashMap<>();
    /**
     * 随机乱置
     * @param arr 原数组
     */
    void shuffle(int[] arr) {
        int n = arr.length;
        // 总共n!次
        for (int i = 0 ; i < n - 1; i++) {
            // 从 i 到最后随机选一个元素
            int rand = randInt(i, n - 1);
            swap(arr, i, rand);
        }
    }

    /**
     * 蒙特卡罗验证
     * 正确性衡量标准是：对于每种可能的结果出现的概率必须相等，也就是说要足够随机。
     */
    void monteCarloValidation() {
        for (int i = 0; i < N; i++) {
            int[] arr = new int[]{1, 2};
            shuffle(arr);
            String ar = Arrays.toString(arr);
            count.put(ar, count.getOrDefault(ar, 0) + 1);
        }
        count.forEach((x, y) -> {
            System.out.println(x + " -> " + y*1.0/N);
        });
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 生成[min, max]区间的整数
    int randInt(int min, int max){
        Random r = new Random();
        return Math.abs(r.nextInt() % (max - min + 1)) + min;
    }

    public static void main(String[] args) {
        ApShuffle apShuffle = new ApShuffle();
        int[] arr = new int[]{1, 4, 6, 2, 40, 5, 34, 67};
        apShuffle.shuffle(arr);
        System.out.println(Arrays.toString(arr));

        apShuffle.monteCarloValidation();
    }
}