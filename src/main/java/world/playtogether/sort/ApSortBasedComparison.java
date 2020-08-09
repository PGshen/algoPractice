package world.playtogether.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * <project> algoPractice
 *
 * <p> 基于比较的排序算法
 *
 * 稳定性：如果a原本在b前面，而a=b，排序之后a仍然在b的前面，则是稳定的；否则是不稳定的
 *
 * @author penggs
 * @since 2020-08-09 10:31
 */
public class ApSortBasedComparison {
    /**
     * 冒泡排序
     * <p>算法描述：
     * 1.比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3.针对所有的元素重复以上的步骤，除了最后一个；
     * 4.重复步骤1~3，直到排序完成。
     *
     * 算法复杂度-> 平均O(n^2), 最坏O(n^2), 最好O(n)
     * 空间复杂度-> O(1)
     * 稳定性-> 稳定
     *
     * @param arr 原始数组
     * @return int[] 排序后的数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        // 循环
        for (int i = 0; i < arr.length; i++) {
            // 相邻的两两对比
            for (int j = 0; j < arr.length - i - 1; j++) {
                // 如果顺序与要排序的不一致，交换位置；此处升序为例
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     * 算法描述：
     * 1. 初始状态：无序区为R[1..n]，有序区为空；
     * 2. 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
     * 3. 该趟排序从当前无序区中-选出关键字最小的记录R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     * 4. n-1趟结束，数组有序化了。
     *
     * 算法复杂度-> 平均O(n^2), 最坏O(n^2), 最好O(n^2)
     * 空间复杂度-> O(1)
     * 稳定性-> 不稳定
     *
     * @param arr 源数组
     * @return int[] 排序后数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] selectionSort(int[] arr) {
        if (arr.length == 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            // 找到未排序区的最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 交换
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    /**
     * 插入排序
     * 算法描述：
     * 1. 从第一个元素开始，该元素可以认为已经被排序；
     * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 5. 将新元素插入到该位置后；
     * 6. 重复步骤2~5
     *
     * 算法复杂度-> 平均O(n^2), 最坏O(n^2), 最好O(n)
     * 空间复杂度-> O(1)
     * 稳定性-> 稳定
     *
     * @param arr 源数组
     * @return int[] 排序后数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] insertionSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int cur;
        for (int i = 1; i < arr.length; i++) {
            cur = arr[i];
            int j = i - 1;
            // 向前比较，直到遇到比不大于当前值的
            for (; j >= 0 && arr[j] > cur; j--) {
                // 大于当前值的向后搬移
                arr[j+1] = arr[j];
            }
            arr[j+1] = cur;
        }
        return arr;
    }

    /**
     * 希尔排序
     * 算法描述：
     * 1. 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
     * 2. 按增量序列个数k，对序列进行k 趟排序；
     * 3. 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     * 算法复杂度-> 平均O(n^1.3), 最坏O(n^2), 最好O(n)
     * 空间复杂度-> O(1)
     * 稳定性-> 不稳定
     *
     * @param arr 源数组
     * @return int[] 排序后数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] shellSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }
        int len = arr.length;
        int temp, gap = len/2;  // gap为增量
        // 按增量进行多次排序
        while (gap > 0) {
            // 以下为一次插入排序
            for (int i = gap; i < len; i++) {
                temp = arr[i];
                int j = i - gap;
                // 向前比较，直到遇到比不大于当前值的；经过前面多次排序，数组已大致有序，下面的循环次数会逐渐减少，从而降低复杂度
                for (; j >= 0 && arr[j] > temp; j=j-gap) {
                    // 大于当前值的向后搬移
                    arr[j+gap] = arr[j];
                }
                arr[j+gap] = temp;
            }
            gap/=2; // 每次取半，形成增量序列
        }
        return arr;
    }

    /**
     * 归并排序
     * 算法描述：
     * 1. 把长度为n的输入序列分成两个长度为n/2的子序列；
     * 2. 对这两个子序列分别采用归并排序；
     * 3. 将两个排序好的子序列合并成一个最终的排序序列。
     *
     * 算法复杂度-> 平均O(nlogn), 最坏O(nlogn), 最好O(nlogn)
     * 空间复杂度-> O(n)
     * 稳定性-> 稳定
     *
     * @param arr 源数组
     * @return int[] 排序后数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] mergeSort(int[] arr) {
        if (arr.length <=1) {
            return arr;
        }
        int mid = arr.length/2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        // 拆分数组进行排序后再合并
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 合并两个有序数组
     * @param left 左边
     * @param right 右边
     * @return 合并后的有序数组
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            // 剩余右边
            if (i >= left.length)
                result[index] = right[j++];
            // 剩余左边
            else if (j >= right.length)
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }

    /**
     * 快速排序
     * 算法描述：
     * 1. 从数列中挑出一个元素，称为 “基准”（pivot）；
     * 2. 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * 3. 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     *
     * 注意点：通过哨兵可实现在源数组上操作，而不需要额外的空间
     *
     * 算法复杂度-> 平均O(nlogn), 最坏O(n^2), 最好O(nlogn)
     * 空间复杂度-> O(nlogn)
     * 稳定性-> 不稳定
     *
     * @param arr 源数组
     * @return int[] 排序后数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] quickSort(int[] arr) {
        quickSortSub(arr, 0, arr.length - 1);
        return arr;
    }

    private static void quickSortSub(int[] a, int head, int tail) {
        // 两个哨兵，分别从两侧向中间靠拢，遇到顺序不对的进行交换
        int low = head;
        int high = tail;
        int pivot = a[low];
        if (low < high) {
            while (low<high) {
                // high哨兵持续向中间，直到遇到值小于区分点的
                while (low < high && pivot <= a[high]) high--;
                // 将这个小于区分点的值搬移到地位
                a[low] = a[high];
                // low哨兵持续向中间，直到遇到值大于区分点的
                while (low < high && pivot >= a[low]) low++;
                a[high]=a[low];
            }
            // 将分区点的值写入中间位置
            a[low] = pivot;
            // 经过上面，以区分点为界，左侧均小于区分点，右侧均大于区分点
            if(low>head+1) quickSortSub(a,head,low-1);
            if(high<tail-1) quickSortSub(a,high+1,tail);
        }
    }

    /**
     * 堆排序
     * 算法描述：
     * 1. 将初始待排序关键字序列(R1,R2….Rn)构建成大顶堆，此堆为初始的无序区；
     * 2. 将堆顶元素R[1]与最后一个元素R[n]交换，此时得到新的无序区(R1,R2,……Rn-1)和新的有序区(Rn),且满足R[1,2…n-1]<=R[n]；
     * 3. 由于交换后新的堆顶R[1]可能违反堆的性质，因此需要对当前无序区(R1,R2,……Rn-1)调整为新堆，然后再次将R[1]与无序区最后一个元素交换，得到新的无序区(R1,R2….Rn-2)和新的有序区(Rn-1,Rn)。不断重复此过程直到有序区的元素个数为n-1，则整个排序过程完成
     *
     * 算法复杂度-> 平均O(nlogn), 最坏O(nlogn), 最好O(nlogn)
     * 空间复杂度-> O(1)
     * 稳定性-> 不稳定
     *
     * @param arr 源数组
     * @return int[] 排序后的数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] heapSort(int[] arr) {
        heapInsert(arr);
        int len = arr.length;
        while (len > 1) {
            swap(arr, 0, len - 1);
            len--;
            heapify(arr, 0, len);
        }
        return arr;
    }

    /**
     * 构建大顶堆
     * 遍历数组，通过新插入的值上升实现堆的调整
     *
     * @param arr 数组
     * @author penggs
     * @since 2020/8/9
     */
    public static void heapInsert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int currentIndex = i;
            int fatherIndex = (currentIndex - 1) / 2;
            //如果当前插入的值大于其父结点的值,则交换值，并且将索引指向父结点，然后继续和上面的父结点值比较，直到不大于父结点，则退出循环
            while (arr[currentIndex] > arr[fatherIndex]) {
                swap(arr, currentIndex, fatherIndex);
                //将当前索引指向父索引
                currentIndex = fatherIndex;
                //重新计算当前索引的父索引
                fatherIndex = (currentIndex - 1) / 2;
            }
        }
    }

    /**
     * 堆调整，栈顶与尾部元素交换后
     *
     * @param arr 数组
	 * @param index
	 * @param size
     * @author penggs
     * @since 2020/8/9
     */
    public static void heapify(int[] arr, int index, int size) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        while (left < size) {
            int largestIndex;
            //判断孩子中较大的值的索引（要确保右孩子在size范围之内）
            if (arr[left] < arr[right] && right < size) {
                largestIndex = right;
            } else {
                largestIndex = left;
            }
            //比较父结点的值与孩子中较大的值，父节点最大说明已经是大根堆，直接退出
            if (arr[index] > arr[largestIndex]) {
                break;
            }
            //父结点不是最大值，与孩子中较大的值交换
            swap(arr, largestIndex, index);
            //将索引指向孩子中较大的值的索引
            index = largestIndex;
            //重新计算交换之后的孩子的索引
            left = 2 * index + 1;
            right = 2 * index + 2;
        }

    }

    //交换数组中两个元素的值
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 二分插入排序
     * 每次通过二分的方式找到插入点
     *
     * 时间复杂度: 平均O(n^2), 最坏O(n^2), 最好O(n)
     * 空间复杂度-> O(1)
     * 稳定性-> 稳定
     *
     * @param arr 源数组
     * @return int[] 排序后数组
     * @author penggs
     * @since 2020/8/9
     */
    public static int[] binaryInsertSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int low = 0;
            int high = i - 1;
            // 找到要插入的中点位置
            while (low <= high) {
                int mid = (low + high) / 2;
                if (temp < arr[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // 数据向后搬移
            for (int j = i - 1; j > high; j--) {
                arr[j+1] = arr[j];
            }
            arr[high+1] = temp;
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
        int[] sourceArr = new int[] {3,34,5,24,666,23,1,-3};
        //int[] sortedArr = bubbleSort(sourceArr);
        //int[] sortedArr = selectionSort(sourceArr);
        //int[] sortedArr = insertionSort(sourceArr);
        //int[] sortedArr = shellSort(sourceArr);
        //int[] sortedArr = mergeSort(sourceArr);
        //int[] sortedArr = quickSort(sourceArr);
        //int[] sortedArr = heapSort(sourceArr);
        int[] sortedArr = binaryInsertSort(sourceArr);
        printArr(sortedArr);
    }
}