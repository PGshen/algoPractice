package world.playtogether.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 计算器
 *
 * @author penggs
 * @since 2021-04-06
 */
public class ApCalculator {
    // 优先级
    Map<Character, Integer> priority = new HashMap<Character, Integer>() {{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
        put('^', 3);
    }};

    public int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        // 清楚空格
        s = s.replaceAll(" ", "");
        // 左括号后面的符号换成0-或0+
        s = s.replaceAll("\\(-", "\\(0-");
        s = s.replaceAll("\\(\\+", "\\(0\\+");
        int len = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < len; i++) {
            char ch = cs[i];
            if (ch == '(') {
                ops.push(ch);
            } else if (ch == ')') {
                // 遇上右括号，开始计算直到遇上左括号
                while (!ops.isEmpty()) {
                    if (ops.peek() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pop();
                        break;
                    }
                }
            } else {
                if (isNumber(ch)) {
                    // 是数字，连续读取
                    int n = ch - '0';
                    int j = i + 1;
                    while (j < len && isNumber(cs[j])) {
                        n = n * 10 + (cs[j++] - '0');
                    }
                    nums.push(n);
                    i = j-1;
                } else {
                    // 是操作符,并且这个操作入栈之前计算前面优先级较高的
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        char prev = ops.peek();
                        if (priority.get(prev) > priority.get(ch)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.push(ch);
                }
            }
        }
        // 读取剩余的
        while (!ops.isEmpty()) {
            calc(nums, ops);
        }
        return nums.peek();
    }

    // 计算
    private void calc(Stack<Integer> nums, Stack<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2 || ops.isEmpty()) return;
        int num1 = nums.pop();
        int num2 = nums.pop();
        char op = ops.pop();
        int res = 0;
        switch (op) {
            case '+': res = num2 + num1; break;
            case '-': res = num2 - num1; break;
            case '*': res = num2 * num1; break;
            case '/': res = num2 / num1; break;
            case '%': res = num2 % num1; break;
            case '^': res = num2 ^ num1; break;
        }
        nums.push(res);
    }

    // 是否数字
    private boolean isNumber(Character c) {
        return Character.isDigit(c);
    }

    public static void main(String[] args) {
        String s = " 3+5 / 2 ";
        System.out.println(new ApCalculator().calculate(s));
    }
}