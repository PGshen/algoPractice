package world.playtogether.hot;

import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 字符串解码
 *
 * @author penggs
 * @since 2021-01-13
 */
public class ApDecodeString {
    public String decodeString(String s) {
        int stackDepth = 0;
        Stack<String> stack = new Stack<>();
        StringBuilder ret = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len;) {
            char ch = s.charAt(i);
            // 数字，入栈
            if (Character.isDigit(ch)) {
                int digit = Integer.parseInt(ch+"");
                while (Character.isDigit(s.charAt(++i))) {
                    digit += digit * 10 + Integer.parseInt(s.charAt(i) + "");
                }
                stack.push(digit + "");
                continue;
            } else if (ch == '[') {
                //stack.push(ch);
                stackDepth++;
            } else if (ch == ']') {
                // 开始出栈

            } else if (stackDepth > 0) {
                //stack.push(ch);
            }
            i++;
        }
        return "";
    }
}