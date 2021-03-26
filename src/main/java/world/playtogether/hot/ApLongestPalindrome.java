package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 最长回文子串
 *
 * @author penggs
 * @since 2020-11-09
 */
public class ApLongestPalindrome {
    /**
     * 思路：以一个字符或两个字符为中心，向两侧展开判断
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 以一个字符为中心
            int len1 = expandAroundCenter(s, i, i);
            // 以两个字符为中心
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            // 根据长度回推起始结束下标
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        // 注意越界
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        // 这里因为是多跑了一轮循环，所以不是R-L+1, 而是R-L-1
        return R - L - 1;
    }

    public static void main(String[] args) {
        String str = "cbbd";
        System.out.println(longestPalindrome(str));
    }
}