package world.playtogether.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * <project> algoPractice
 *
 * <p> 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 ""
 *
 * @author penggs
 * @since 2020-11-27
 */
public class ApMinWindowSubStr {
    /**
     * 双指针滑动窗口
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        // 记录需要多少个相应的字符
        Map<Character, Integer> need = new HashMap<>();
        for (char ch: t.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        int left = 0, right = 0, start = 0, needCount = t.length(), minSize = Integer.MAX_VALUE;
        while (right < s.length()) {
            char ch = s.charAt(right);
            // 是需要的字符，needCount--
            int chCount = need.getOrDefault(ch, 0);
            // 只有需要的字符计数才会大于0
            if (chCount > 0) {
                needCount--;
            }
            need.put(ch, --chCount);
            // 足够包含了
            if (needCount == 0) {
                // 移动左指针，知道不满足
                while (left < right && need.getOrDefault(s.charAt(left), 0) < 0) {
                    char leftCh = s.charAt(left);
                    need.put(leftCh, need.getOrDefault(leftCh, 0) + 1);
                    left++;
                }
                // 挑战是否最小
                if (right-left+1 < minSize) {
                    minSize = right-left+1;
                    start = left;
                }
                // 最后移动一次左指针，使其不符合
                need.put(s.charAt(left), 1+need.getOrDefault(s.charAt(left), 0));
                left++;
                needCount++;
            }
            // 移动右指针
            right++;
        }
        return minSize == Integer.MAX_VALUE ? "" : s.substring(start, start+minSize);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("a", "aa"));
    }
}