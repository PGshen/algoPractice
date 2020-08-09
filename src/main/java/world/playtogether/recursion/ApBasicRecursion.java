package world.playtogether.recursion;

/**
 * <project> algoPractice
 *
 * <p> 递归基础操作
 * 递归必须满足的三个条件：
 * 1. 一个问题的解可以分解为几个子问题的解
 * 2. 这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样
 * 3. 存在递归终止条件
 *
 * 写递归代码的关键就是找到如何将大问题分解为小问题的规律，并且基于此写出递推公式，然后再推敲终止条件，最后将递推公式和终止条件翻译成代码。
 *
 * 注意点：递归代码要警惕堆栈溢出、递归代码要警惕重复计算、可以考虑将递归代码改写为非递归代码
 *
 * @author penggs
 * @since 2020-08-09 10:02
 */
public class ApBasicRecursion {

    /**
     * 假如这里有 n 个台阶，每次你可以跨 1 个台阶或者 2 个台阶，请问走这 n 个台阶有多少种走法？
     *
     * @param stepsCount 台阶数
     * @return int 走法总数
     * @author penggs
     * @since 2020/8/9
     */
    public int countSteps(int stepsCount) {
        if (stepsCount <= 0) {
            throw new IllegalArgumentException("stepsCount must be greater than zero");
        }
        // 终止条件，当台阶数为1时，有一种走法；当台阶为2时，有两种走法
        if (stepsCount == 1) return 1;
        if (stepsCount == 2) return 2;
        // 递归：当台阶数为stepsCount时，走法为 台阶数是stepsCount-1走法 与 台阶数是stepsCount-2走法的和
        return countSteps(stepsCount - 1) + countSteps(stepsCount - 2);
    }

    /**
     * 台阶走法数的非递归实现
     * 一般可以先递归实现，然后考虑将递归转为非递归
     *
     * @param stepsCount 台阶数
     * @return int 走法总数
     * @author penggs
     * @since 2020/8/9
     */
    public int countSteps2(int stepsCount) {
        if (stepsCount <= 0) {
            throw new IllegalArgumentException("stepsCount must be greater than zero");
        }
        if (stepsCount == 1) return 1;
        if (stepsCount == 2) return 2;
        int ret = 0;    // 记录当前台阶数的走法数
        int pre = 2;    // 记录前1个台阶的走法数
        int prepre = 1; // 记录前2个台阶的走法数
        for (int i = 3; i <= stepsCount; ++i) {
            ret = pre + prepre;
            prepre = pre;
            pre = ret;
        }
        return ret;
    }

    public static void main(String[] args) {
        ApBasicRecursion apBasicRecursion = new ApBasicRecursion();
        System.out.println(apBasicRecursion.countSteps(5));
        System.out.println(apBasicRecursion.countSteps2(5));
    }
}