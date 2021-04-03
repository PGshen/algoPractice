package world.playtogether.stack;

import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 逆波兰表达式求值
 *
 * @author penggs
 * @since 2021-04-03
 */
public class ApEvalRPN {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token: tokens) {
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                Integer first = stack.pop();
                Integer last = stack.pop();
                switch (token) {
                    case "+": stack.push(first + last); break;
                    case "-": stack.push(last - first); break;
                    case "*": stack.push(first * last); break;
                    case "/": stack.push(last / first); break;
                }
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}