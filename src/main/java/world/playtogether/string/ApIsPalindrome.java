package world.playtogether.string;

/**
 * <project> algoPractice
 *
 * <p> 判断字符串是否是回文（忽略非字母和数字）
 *
 * @author penggs
 * @since 2021-04-26 19:55
 */
public class ApIsPalindrome {
    public static boolean isPalindrome(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        int i = 0;
        int j = len - 1;
        while (i<j) {
            while (i < len && !Character.isDigit(chs[i]) && !Character.isLetter(chs[i])) i++;
            while (j >= 0 && !Character.isDigit(chs[j]) && !Character.isLetter(chs[j])) j--;
            if ((chs[i] | ' ') != (chs[j] | ' ')) return false; // 转换为小写
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }
}