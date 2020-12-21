package world.playtogether.other;

import java.util.Random;

/**
 * <project> algoPractice
 *
 * <p> 随机5转为随机7
 *
 * @author penggs
 * @since 2020-12-16
 */
public class ApRand5ToRand7 {
    public static int rand7() {
        //  用2位的5进制表达7进制
        int v = rand5() * 5 + rand5();
        // 再合适的范围内求余
        if (v < 21) {
            return v % 7;
        }
        return rand7();
    }

    static int rand5() {
        return new Random().nextInt(5);
    }
}