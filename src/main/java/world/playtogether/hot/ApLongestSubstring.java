package world.playtogether.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * <project> algoPractice
 *
 * <p> 最长连续不重复字串
 *
 * @author penggs
 * @since 2020-11-09
 */
public class ApLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        // 缓存窗口
        Map<Character, Integer> map = new HashMap<>();
        int len = s.length();
        int j = 0;
        int maxLen = 0;
        // 双指针移动
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            // 窗口已包含该字符，移动前指针知道不包含
            if (map.containsKey(ch)) {
                int temp = map.get(ch) + 1;
                for (;j < temp; j++) {
                    map.remove(s.charAt(j));
                }
            }
            map.put(ch, i);
            if (map.size() > maxLen) maxLen = map.size();
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcdbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}