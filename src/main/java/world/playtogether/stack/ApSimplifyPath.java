package world.playtogether.stack;

import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 简化unix路径
 *
 * @author penggs
 * @since 2021-04-02
 */
public class ApSimplifyPath {
    public static String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/");
        for (String s : paths) {
            if ("..".equals(s)) {
                if (!stack.empty()) {
                    stack.pop();
                }
            } else if (!".".equals(s) && !"".equals(s)) {
                stack.push(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.insert(0, "/" + stack.pop());
        }
        if (sb.length() == 0) {
            sb = new StringBuilder("/");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String path = "/a/./b/../../c/";
        System.out.println(simplifyPath(path));
    }
}