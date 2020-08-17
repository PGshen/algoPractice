package world.playtogether.strmatch;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> KMP算法
 *
 * @author penggs
 * @since 2020-08-17
 */
public class ApKmp {
    /**
     * 模式串的预处理
     * 构造模式串 pattern 的最大匹配数表，最大匹配数为 模式串子串的前缀子串和后缀子串匹配的最大长度
     * 次大匹配必定在最大匹配中，所以次大匹配数就是 最大匹配中 的最大匹配数！
     * 由于是前缀子串，所以maxLength记录的刚好是最大匹配前缀子串下一字符索引位，该位置正好对应于下一次需要比较的字符
     * @param pattern 模式串
     * @return 匹配表
     */
    int[] calculateMaxMatchLengths(String pattern) {
        int[] maxMatchLengths = new int[pattern.length()];
        int maxLength = 0;
        // 根据规律，已知p[0]...p[i-1]的最大匹配数，求p[i]的最大匹配数
        for (int i = 1; i < pattern.length(); i++) {
            while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
                maxLength = maxMatchLengths[maxLength - 1]; // ①
            }
            if (pattern.charAt(maxLength) == pattern.charAt(i)) {
                maxLength++; // ②
            }
            maxMatchLengths[i] = maxLength;
        }
        return maxMatchLengths;
    }

    // 在文本 text 中寻找模式串 pattern，返回所有匹配的位置开头
    List<Integer> search(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();
        int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
                count = maxMatchLengths[count - 1];
            }
            if (pattern.charAt(count) == text.charAt(i)) {
                count++;
            }
            if (count == pattern.length()) {
                positions.add(i - pattern.length() + 1);
                // 因为当前以匹配完成，所以模式串回退到上一次最大匹配位置
                count = maxMatchLengths[count - 1];
            }
        }
        return positions;
    }

    public static void main(String[] args) {
        ApKmp apKmp = new ApKmp();
        System.out.println(apKmp.search("iAmYouYou", "ou"));
    }
}