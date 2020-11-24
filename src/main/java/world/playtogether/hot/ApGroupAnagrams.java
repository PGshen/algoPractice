package world.playtogether.hot;

import java.util.*;

/**
 * <project> algoPractice
 *
 * <p> 字母异位词分组
 * 借助map，关键在如何判断异位词
 *
 * @author penggs
 * @since 2020-11-24
 */
public class ApGroupAnagrams {
    /**
     * 解法一，通过对原子串进行排序来区分是否是异位词
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            // 排序后判断是否一致
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            String chStr = String.valueOf(ch);
            if (!map.containsKey(chStr)) {
                map.put(chStr, new ArrayList<>());
            }
            map.get(chStr).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 解法二：通过将字符串写入字符数组判断是否为异位词
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] ca = new char[26];
            for (char ch:str.toCharArray()) {
                ca[ch - 'a']++;
            }
            String chStr = String.valueOf(ca);
            if (!map.containsKey(chStr)) {
                map.put(chStr, new ArrayList<>());
            }
            map.get(chStr).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
        System.out.println(groupAnagrams2(strs));
    }
}