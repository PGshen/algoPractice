package world.playtogether.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * <project> algoPractice
 *
 * <p> 基于非比较的排序算法
 *
 * @author penggs
 * @since 2020-08-09 10:31
 */
public class ApSortBasedNonComparison {


    /**
     * 桶排序
     * 算法描述：
     * 1. 设置一个定量的数组当作空桶；
     * 2. 遍历输入数据，并且把数据一个一个放到对应的桶里去；
     * 3. 对每个不是空的桶进行排序；
     * 4. 从不是空的桶里把排好序的数据拼接起来。
     *
     * 算法复杂度-> 平均O(n+k), 最坏O(n^2), 最好O(n)
     * 空间复杂度-> O(n+k)
     * 稳定性-> 稳定(实际取决于桶内所用的排序算法)
     *
     * 适用场景：桶排序比较适合用在外部排序中。
     * 所谓的外部排序就是数据存储在外部磁盘中，数据量比较大，内存有限，无法将数据全部加载到内存中。
     *
     * @param arr 源数组
     * @return int[] 排序后数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] bucketSort(int[] arr) {
        // 计算最大值与最小值
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int value : arr) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        }

        // 计算桶的数量
        int bucketNum = (max - min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<Integer>());
        }

        // 将每个元素放入桶
        for (int value : arr) {
            int num = (value - min) / (arr.length);
            bucketArr.get(num).add(value);
        }

        // 对每个桶进行排序
        for (ArrayList<Integer> integers : bucketArr) {
            Collections.sort(integers);
        }

        // 将桶中的元素赋值到原序列
        int index = 0;
        for (ArrayList<Integer> integers : bucketArr) {
            for (Integer integer : integers) {
                arr[index++] = integer;
            }
        }
        return arr;
    }

    /**
     * 计数排序
     * 算法描述：
     * 1. 找出待排序的数组中最大和最小的元素；
     * 2. 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
     * 3. 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
     * 4. 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
     *
     * 注意点：如果数值有负数、小数等情况，须先做转换处理
     *
     * 算法复杂度-> 平均O(n+k), 最坏O(n+k), 最好O(n+k)
     * 空间复杂度-> O(n+k)
     * 稳定性-> 稳定
     *
     * @param arr 源数组
     * @return int[] 排序后数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] countingSort(int[] arr) {
        if (arr.length == 0) return arr;
        int bias, min = arr[0], max = arr[0];
        // 找出最大值最小值
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        // 桶统计元素出现的次数
        bias = -min;     //偏移量
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int value : arr) {
            bucket[value + bias]++;
        }
        // 发现填充
        int index = 0, i = 0;
        while (index < arr.length) {
            if (bucket[i] != 0) {
                arr[index] = i - bias;
                bucket[i]--;    // 取出后，数量应减一
                index++;
            } else {
                i++;
            }
        }
        return arr;
    }

    /**
     * 计数排序
     * 算法描述：
     * 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。
     * 1. 取得数组中的最大数，并取得位数；
     * 2. arr为原始数组，从最低位开始取每个位组成radix数组；
     * 3. 对radix进行计数排序（利用计数排序适用于小范围数的特点）
     *
     * 算法复杂度-> 平均O(n*k), 最坏O(n*k), 最好O(n*k)
     * 空间复杂度-> O(n+k)
     * 稳定性-> 稳定
     *
     * @param arr 源数组
     * @return int[] 排序后数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return null;
        }
        // 1. 找出最大值，然后取最大位置
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /=10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        // 初始化桶，10个对应10个数字
        ArrayList<ArrayList<Integer>> bucketList =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<>());
        }
        // 按位数循环
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            // 根据当前位对应值分桶
            for (int value : arr) {
                int num = value % mod / div;
                bucketList.get(num).add(value);
            }
            // 按照痛的顺序进行收集
            int index = 0;
            for (ArrayList<Integer> bucket : bucketList) {
                for (Integer integer : bucket) {
                    arr[index++] = integer;
                }
                bucket.clear();
            }
        }
        return arr;
    }

    public static void printArr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int value : arr) {
            sb.append(value).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    public static void main(String[] args) {
        int[] sourceArr = new int[] {3,34,5,24,666,23,1,3};
        //int[] sortedArr = bucketSort(sourceArr);
        //int[] sortedArr = countingSort(sourceArr);
        int[] sortedArr = radixSort(sourceArr);
        printArr(sortedArr);
    }
}