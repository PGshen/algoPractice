package world.playtogether.devideandconquer;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 为运算表达式设计优先级
 *
 * @author penggs
 * @since 2021-04-18 16:48
 */
public class ApDiffWaysToCompute {
    /**
     * 分治算法，在符号两边加括号，然后递归
     * @param expression
     * @return
     */
    public static List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ret = new ArrayList<>();
        int len = expression.length();
        for(int i = 0; i < len; i++) {
            char ch = expression.charAt(i);
            if(ch == '+' || ch == '-' || ch == '*') {
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i+1));
                for(int j: left) {
                    for(int k: right) {
                        if(ch == '+') {
                            ret.add(j + k);
                        } else if(ch == '-') {
                            ret.add(j - k);
                        } else if(ch == '*') {
                            ret.add(j * k);
                        }
                    }
                }
            }
        }
        // 没有符号，只有值
        if (ret.isEmpty()) ret.add(Integer.parseInt(expression));
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(diffWaysToCompute("2-1-1"));
    }
}