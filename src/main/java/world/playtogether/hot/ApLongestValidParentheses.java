package world.playtogether.hot;

import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 最长有效括号
 *
 * @author penggs
 * @since 2020-11-16
 */
public class ApLongestValidParentheses {
    public static int longestValidParentheses(String s) {
        return Math.min(func1(s.toCharArray()), func2(reverse(s.toCharArray())));
    }


    public static int func1(char[] chars) {
        Stack<Character> stack = new Stack<>();
        int sum = 0;
        int res = 0;
        int left = 0, right = 0;
        for (Character ch: chars) {
            if (ch == '(') {
                left++;
                stack.push(ch);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                    sum += 2;
                    res = Math.max(sum, res);
                }
                right++;
            }
            if (right > left) {
                stack.clear();
                left = 0;
                right = 0;
                sum = 0;
            }
        }
        return res;
    }

    public static int func2(char[] chars) {
        Stack<Character> stack = new Stack<>();
        int sum = 0;
        int res = 0;
        int left = 0, right = 0;
        for (Character ch: chars) {
            if (ch == ')') {
                right++;
                stack.push(ch);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                    sum += 2;
                    res = Math.max(sum, res);
                }
                left++;
            }
            if (left > right) {
                stack.clear();
                left = 0;
                right = 0;
                sum = 0;
            }
        }
        return res;
    }



    private static char[] reverse(char[] chars) {
        int i = 0, j = chars.length - 1;
        while (i < j) {
            swap(chars, i, j);
            i++;
            j--;
        }
        return chars;
    }

    /**
     * 交换
     * @param chars 数组
     * @param i i
     * @param j j
     */
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("()(()"));

    }
}