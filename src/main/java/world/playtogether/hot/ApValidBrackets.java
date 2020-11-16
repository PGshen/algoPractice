package world.playtogether.hot;

import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 有效的括号
 *
 * @author penggs
 * @since 2020-11-15
 */
public class ApValidBrackets {
    /**
     * 借助栈来判断
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s.isEmpty()) return true;
        Stack<Character> stack = new Stack<>();
        for (char ch: s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{'){
                stack.push(ch);
            } else if (ch == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                return false;
            } else if (ch == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                return false;
            } else if (ch == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 转换一下入栈的
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        if(s.isEmpty())
            return true;
        Stack<Character> stack=new Stack<>();
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            else if(stack.empty()||c!=stack.pop())
                return false;
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("{}()[]{{()}}"));
    }
}