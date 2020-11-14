package world.playtogether.hot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <project> algoPractice
 *
 * <p> 电话数字按键字母组合
 *
 * @author penggs
 * @since 2020-11-14
 */
public class ApLetterCombinations {
    static Map<Character, String> digitLetterMap;
    static {
        digitLetterMap = new HashMap<>();
        digitLetterMap.put('2', "abc");
        digitLetterMap.put('3', "def");
        digitLetterMap.put('4', "ghi");
        digitLetterMap.put('5', "jkl");
        digitLetterMap.put('6', "mno");
        digitLetterMap.put('7', "pqrs");
        digitLetterMap.put('8', "tuv");
        digitLetterMap.put('9', "wxyz");
    }

    /**
     * 每次按键前，先将队列内全部出队，跟新字符连接后再次入队
     * @param digits 数字
     * @return
     */
    public static List<String> letterCombinations1(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        res.add("");
        for (int i = 0; i < digits.length(); i++) {
            String letters = digitLetterMap.get(digits.charAt(i));
            // 出队列
            int resSize = res.size();
            for (int k = 0; k < resSize; k++) {
                String ch = res.remove(0);
                // 跟新字符连接后再入队
                for (int j = 0; j < letters.length(); j++) {
                    res.add(ch + letters.charAt(j));
                }
            }
        }
        return res;
    }

    /**
     * 回溯法
     * @param digits 数字
     * @return
     */
    public static List<String> letterCombinations2(String digits) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        findCombination(digits, 0, sb, res);
        return res;
    }

    /**
     * 回溯-递归
     * @param digits 数字
     * @param index 第几位
     * @param sb 当前拼接的
     * @param res 结果集
     */
    private static void findCombination(String digits, int index, StringBuilder sb, List<String> res) {
        // 符合条件，加入结果集
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        String letters = digitLetterMap.get(digits.charAt(index));
        for (int i = 0; i < letters.length(); i++) {
            // 做选择
            sb.append(letters.charAt(i));
            // 递归
            findCombination(digits, index+1, sb, res);
            // 撤销选择
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations1("23"));
        System.out.println(letterCombinations2("23"));
    }
}