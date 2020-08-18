package world.playtogether.charmatch;

/**
 * <project> algoPractice
 *
 * <p> Rabin-Karp匹配算法
 * 算法原理： 将字符的比较转为哈希值的比较；
 * 通过比较主串的每个子串的哈希值与模式串的哈希值进行比对，如果哈希值一致再比较具体每个字符，
 * 哈希值的比较比起直接比较字符串计算量减小
 * 时间复杂度就是 O(n)。
 *
 * @author penggs
 * @since 2020-08-15
 */
public class ApRabinKarp {
    public int rk(String masterStr, String patternStr) {
        int m = masterStr.length();
        int n = patternStr.length();
        // 模式串的哈希值
        long patternHash = hash(patternStr);
        char[] mChars = masterStr.toCharArray();
        char[] pChars = patternStr.toCharArray();
        for (int i = 0; i <= m - n; i++) {
            // 主串的子串的哈希值与模式串的哈希值进行比较
            // TODO 计算哈希值可以优化，因为相邻的子串存在很多重复的字符
            if (hash(mChars, i, i + n) == patternHash) {
                // 哈希值一致，但由于存在哈希冲突，所以进行一次实际的比较
                if (isEqual(mChars, i, i + n, pChars)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 两个字符串是否完全匹配
     *
     * @param masterStr 主串
	 * @param patternStr 模式串
     * @return boolean
     * @author penggs
     * @since 2020/8/15
     */
    public static boolean isEqual(String masterStr, String patternStr) {
        int m = masterStr.length();
        int n = patternStr.length();
        if (m != n) {
            return false;
        }
        char[] mChars = masterStr.toCharArray();
        char[] pChars = patternStr.toCharArray();
        for (int i = 0; i < m; i++) {
            if (mChars[i] != pChars[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEqual(char[] mChars, int begin, int end, char[] pChars) {
        for (int i = begin, j = 0; i < end; i++, j++) {
            if (mChars[i] != pChars[j]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 计算字符串的哈希值，通过各个字符的ascii值相加得到，不容易出现溢出的情况，但存在一定的冲突，后续处理来解决
     *
     * @param str 字符串
     * @return long 哈希值
     * @author penggs
     * @since 2020/8/15
     */
    public static long hash(String str) {
        long hashVal = 0;
        char[] strChars = str.toCharArray();
        for (char strChar : strChars) {
            hashVal += strChar;
        }
        return hashVal;
    }

    public static long hash(char[] chars, int begin, int end) {
        long hashVal = 0;
        for (int i = begin; i < end; i++) {
            hashVal += chars[i];
        }
        return hashVal;
    }

    public static void main(String[] args) {
        ApRabinKarp apRabinKarp = new ApRabinKarp();
        System.out.println(ApRabinKarp.hash("AB"));
        System.out.println(apRabinKarp.rk("ABBBBBKKKD", "BKK"));
        System.out.println(apRabinKarp.rk("ABBBBBKKKD", "BBBB"));
        System.out.println(apRabinKarp.rk("ABBBBBKKKD", "BBBBD"));
    }
}