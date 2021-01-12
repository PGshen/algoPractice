package world.playtogether.hot;

import java.util.Arrays;

/**
 * 比特位计数
 */
public class ApCountBits {
    /**
     * 遍历计算每个数的二进制的1的个数
     * @param num
     * @return
     */
    public int[] countBits1(int num) {
        int[] ret = new int[num + 1];
        for (int i = 0; i < num + 1; i++) {
            int count = 0;
            int n = i;
            while (n != 0) {
                // 消除最低位的1
                n &= n-1;
                count++;
            }
            ret[i] = count;
        }
        return ret;
    }

    /**
     * 通过奇偶数的规律计算
     * 奇数：比前面的偶数多一个1
     * 偶数：1的个数同当前数除以2的偶数一致，除以2相当于右移一位，把低位的0抹掉
     * @param num
     * @return
     */
    public int[] countBits2(int num) {
        int[] ret = new int[num + 1];
        ret[0] = 0;
        for (int i = 0; i <= num; i++) {
            if (i % 2 == 1) {
                ret[i] = ret[i-1] + 1;
            } else {
                ret[i] = ret[i >> 1];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new ApCountBits().countBits1(5)));
        System.out.println(Arrays.toString(new ApCountBits().countBits2(5)));
    }
}
