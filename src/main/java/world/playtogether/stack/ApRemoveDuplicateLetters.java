package world.playtogether.stack;

import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 去除重复字母
 *
 * @author penggs
 * @since 2021-04-08 09:30
 */
public class ApRemoveDuplicateLetters {
    String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        int[] count = new int[256];
        char[] cs = s.toCharArray();
        int len = s.length();
        // 记录每个字符出现的次数
        for (int i = 0; i < len; i++) {
            count[cs[i]]++;
        }
        // 记录是否在栈中
        boolean[] inStack = new boolean[256];
        for (int i = 0; i < len; i++) {
            char ch = cs[i];
            count[ch]--;
            if (inStack[ch]) continue;

            while (!stack.empty() && stack.peek() > ch) {
                if (count[stack.peek()] != 0) {
                    // 后面还有，弹出
                    inStack[stack.pop()] = false;
                } else {
                    break;
                }
            }
            stack.push(ch);
            inStack[ch] = true;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }
}