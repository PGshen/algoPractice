package world.playtogether.math;

import java.util.Arrays;

/**
 * <project> algoPractice
 *
 * <p> 计算素数个数
 *
 * @author penggs
 * @since 2020-10-21
 */
public class ApPrime {
    /**
     * 暴力法
     * @param n
     * @return
     */
    int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrime(i)) count++;
        return count;
    }

    // 判断整数 n 是否是素数
    boolean isPrime(int n) {
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                // 有其他整除因子
                return false;
        return true;
    }

    // 判断整数 n 是否是素数
    boolean isPrime2(int n) {
        // 只需判断到 Math.sqrt便可以直到是否是素数
        for (int i = 2; i < Math.sqrt(n); i++)
            if (n % i == 0)
                // 有其他整除因子
                return false;
        return true;
    }

    /**
     * 利用倍数排除法
     * @param n
     * @return
     */
    int countPrimes2(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        // 只需要到Math.sqrt(n)
        for (int i = 2; i < Math.sqrt(n); i++)
            if (isPrim[i])
                // i 的倍数 肯定不是素数，标记以下。并且起始数可以从 i^2开始，避免重复计算
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        // 遍历一遍剩余的
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }

    public static void main(String[] args) {
        ApPrime apPrime = new ApPrime();
        System.out.println(apPrime.countPrimes(20) == apPrime.countPrimes2(20));
        System.out.println(apPrime.countPrimes(200) == apPrime.countPrimes2(200));
    }
}