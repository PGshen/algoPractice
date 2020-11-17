package world.playtogether.search;

/**
 * <project> algoPractice
 *
 * <p> 二分查找
 * 局限性：
 * 1.二分查找依赖的是顺序表结构，简单点说就是数组
 * 2.二分查找针对的是有序数据
 * 3.数据量太小不适合二分查找
 * 4.数据量太大也不适合二分查找，因为数组需要连续的内存空间，过大可能导致申请不到可用的空间
 *
 * @author penggs
 * @since 2020-08-09 16:56
 */
public class ApBinarySearch {
    /**
     * 基于迭代的二分查找
     * 前提条件，有序且不存在重复的值
     *
     * @param arr   数组
     * @param value 查找值
     * @return int 下标
     * @author penggs
     * @since 2020/8/9
     */
    public static int bSearch1(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        // 退出循环的条件是low <= high,而不是low < high
        while (low <= high) {
            // 直接(low + high)/2 有可能溢出
            int mid = low + ((high - low) >> 1);
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找的递归实现
     * 前提条件，有序且不存在重复的值
     *
     * @param arr 数组
	 * @param value 查找值
     * @return int 下标
     * @author penggs
     * @since 2020/8/9
     */
    public static int bSearch2(int[] arr, int value) {
        return bSearch2Internally(arr, 0, arr.length - 1, value);
    }

    /**
     * 递归
     *
     * @param arr 数组
	 * @param low 低位下标
	 * @param high 高位下标
	 * @param value 查找值
     * @return int 下标
     * @author penggs
     * @since 2020/8/9
     */
    private static int bSearch2Internally(int[] arr, int low, int high, int value) {
        if (low > high) return -1;
        int mid = low + ((high - low) >> 1);
        if (arr[mid] == value) {
            return mid;
        } else if (arr[mid] < value) {
            return bSearch2Internally(arr, mid + 1, high, value);
        } else {
            return bSearch2Internally(arr, low, mid - 1, value);
        }
    }

    /**
     * 查找第一个值等于给定值的元素
     * @param arr 数组
     * @param value 查找值
     * @return 下标
     */
    public static int bSearchFirst(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || arr[mid-1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个值等于给定值的元素
     * @param arr 数组
     * @param value 查找值
     * @return 下标
     */
    public static int bSearchLast(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] > value) {
                high = mid - 1;
            } else if (arr[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == arr.length - 1 || arr[mid+1] != value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     * @param arr 数组
     * @param value 查找值
     * @return 下标
     */
    public static int bSearchFirstGreaterThan(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= value) {
                if (mid == 0 || arr[mid-1] < value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     * @param arr 数组
     * @param value 查找值
     * @return 下标
     */
    public static int bSearchLastLessThan(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (arr[mid] <= value) {
                if (mid == 0 || arr[mid+1] > value) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] sourceArr1 = new int[] {1, 3, 5, 7, 9, 22, 35, 67, 77};
        System.out.println(bSearch1(sourceArr1, 22));
        System.out.println(bSearch2(sourceArr1, 22));
        int[] sourceArr2 = new int[] {1, 3, 5, 9, 22, 22, 22, 35, 67, 77};
        //int[] sourceArr2 = new int[] {22};
        System.out.println(bSearchFirst(sourceArr2, 22));
        System.out.println(bSearchLast(sourceArr2, 22));
        int[] sourceArr3 = new int[] {1, 3, 5, 9, 22, 22, 22, 35, 67, 77};
        System.out.println(bSearchFirstGreaterThan(sourceArr3, 23));
        System.out.println(bSearchLastLessThan(sourceArr3, 23));
    }
}