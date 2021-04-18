package world.playtogether.hot;

import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author penggs
 * @since 2021-01-13
 */
public class ApDecodeString {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        // 两个栈分别保存数字和字符串
        Stack<Integer> multiStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        for (Character ch: s.toCharArray()) {
            if (ch == '[') {
                // 将之前的入栈
                multiStack.push(multi);
                strStack.push(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (ch == ']') {
                // 出栈
                StringBuilder tmp = new StringBuilder();
                int curMulti = multiStack.pop();
                for (int i = 0; i < curMulti; i++) {
                    tmp.append(res);
                }
                res = new StringBuilder(strStack.pop() + tmp.toString());
            } else if (Character.isDigit(ch)) {
                multi = multi * 10 + Integer.parseInt(ch + "");
            } else {
                res.append(ch);
            }
        }
        return res.toString();
    }
}