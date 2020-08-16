package world.playtogether.strmatch;

/**
 * <project> algoPractice
 *
 * <p> 朴素匹配算法
 *
 * @author penggs
 * @since 2020-08-15
 */
public class ApBruteForce {
    /**
     * 暴力匹配
     *
     * @param masterStr 主串
	 * @param patternStr 模式串
     * @return int 匹配位置
     * @author penggs
     * @since 2020/8/15
     */
    public int bf(String masterStr, String patternStr) {
        int m = masterStr.length();
        int n = patternStr.length();
        // 转为字符数组
        char[] mChars = masterStr.toCharArray();
        char[] pChars = patternStr.toCharArray();
        for (int i = 0; i <= m - n; i++) {
            int k = 0;  // 成功匹配的字符数
            for (int j = 0; j < n; j++) {
                if (mChars[i+j] == pChars[j]) {
                    k++;
                } else {
                    // 不匹配，直接结束
                    break;
                }
            }
            // 匹配数和模式串的长度一致，说明匹配完成
            if (k==n) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        ApBruteForce apBruteForce = new ApBruteForce();
        System.out.println(apBruteForce.bf("ZZABCDDDDD", "BCD"));
        System.out.println(apBruteForce.bf("ABCDDDDD", "bd"));
    }
}