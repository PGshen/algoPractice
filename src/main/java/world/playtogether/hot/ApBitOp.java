package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 位操作
 *
 * @author penggs
 * @since 2021-04-18 17:06
 */
public class ApBitOp {
    /**
     * 计算一个数的二进制表示，1的个数。 汉明权重
     * 利用按位与操作，每次消除二进制数的最后一个1，数字变为0
     * 例如 n = 11, 二进制则表示1011， 那么n-1的二进制表示位1010
     * n & (n-1) 结果为 1010
     * @param n
     * @return
     */
    public static int countHammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n-1);
            res++;
        }
        return res;
    }

    /**
     * 判断是否是2的指数
     * 一个数如果是 2 的指数，那么它的二进制表示一定只含有一个 1
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }

    /**
     * 只出现一次的值
     * 利用异或运算。a ^ a = 0     a ^ 0 = a
     * @param nums
     * @return
     */
    int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countHammingWeight(11));
    }
}