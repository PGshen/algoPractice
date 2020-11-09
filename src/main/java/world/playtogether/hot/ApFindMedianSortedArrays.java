package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 查找有序数组的中位数
 *
 * @author penggs
 * @since 2020-11-09
 */
public class ApFindMedianSortedArrays {
    /**
     * 解法一：暴力解法
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int[] arr = new int[length];
        // 合并
        merge(arr, nums1, nums2);
        // 区分是偶数个还是奇数个
        if (length % 2 == 0) {
            return (arr[length/2] + arr[length/2-1]) * 0.5;
        } else {
            return arr[length/2];
        }
    }

    /**
     * 合并有序数组
     * @param mergeArr
     * @param nums1
     * @param nums2
     */
    private static void merge(int[] mergeArr, int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int k = 0;
        while (i < nums1.length || j < nums2.length) {
            if (i == nums1.length) {
                mergeArr[k++] = nums2[j++];
            } else if (j == nums2.length) {
                mergeArr[k++] = nums1[i++];
            } else if (nums1[i] < nums2[j]) {
                mergeArr[k++] = nums1[i++];
            } else {
                mergeArr[k++] = nums2[j++];
            }
        }
    }


    /**
     * 解法二：将问题转化为求第k大的数，然后使用二分法降低时间复杂度o(log(m+n))
     * 边界情况处理。。。。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;

        // 其中一个已经为0了
        if (len1 == 0) return nums2[start2 + k - 1];
        if (len2 == 0) return nums1[start1 + k - 1];
        // 取第1小的
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        // 常规情况
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }


    public static void main(String[] args) {
        int[] num1 = new int[]{1, 2};
        int[] num2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(num1, num2));
        System.out.println(findMedianSortedArrays2(num1, num2));
    }
}