package world.playtogether.strmatch;

/**
 * <project> algoPractice
 *
 * <p> BM 匹配算法
 *
 * @author penggs
 * @since 2020-08-16
 */
public class ApBoyerMoore {
    private static final int SIZE = 256; // 全局变量或成员变量

    /**
     * 匹配
     * @param masterStr 主串
     * @param patternStr 模式串
     * @return 匹配到位置
     */
    public int bm(String masterStr, String patternStr) {
        return bm(masterStr.toCharArray(), masterStr.length(), patternStr.toCharArray(), patternStr.length());
    }

    /**
     * 匹配
     * @param a 主串
     * @param n 主串长度
     * @param b 模式串
     * @param m 模式串长度
     * @return 匹配的位置
     */
    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b, m, bc); // 构建坏字符哈希表
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        int i = 0; // j表示主串与模式串匹配的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            // 坏字符规则计算得到的滑动距离，x可能为负数（此时不可用）
            int x = j - bc[(int)a[i+j]];
            // 好后缀规则计算得到的滑动距离
            int y = 0;
            if (j < m-1) { // 如果有好后缀的话
                y = moveByGS(j, m, suffix, prefix);
            }
            // 综合以上两种规则，去最大值
            i = i + Math.max(x, y);
        }
        return -1;
    }

    /**
     * 字符在模式串的位置，索引位大的会替换小的，哈希映射表
     *
     * @param b 模式串
	 * @param m 模式串长度
	 * @param bc 映射表
     */
    private void generateBC(char[] b, int m, int[] bc) {
        for (int i = 0; i < SIZE; ++i) {
            bc[i] = -1; // 初始化bc
        }
        for (int i = 0; i < m; ++i) {
            int ascii = (int)b[i]; // 计算b[i]的ASCII值
            bc[ascii] = i;
        }
    }

    /**
     * 好后缀规则预处理
     * @param b 模式串
     * @param m 模式串长度
     * @param suffix 索引位对应后缀子串长度（长度来代表不同的后缀子串）存储在模式串中跟好后缀{u}相匹配的子串{u*}的起始下标值
     * @param prefix 索引位对应后缀子串长度（长度来代表不同的后缀子串）记录模式串的后缀子串是否能匹配模式串的前缀子串
     */
    private void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; ++i) { // 初始化
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; ++i) { // b[0, i]
            int j = i;
            int k = 0; // 公共后缀子串长度
            while (j >= 0 && b[j] == b[m-1-k]) { // 与b[0, m-1]求公共后缀子串
                --j;
                ++k;
                suffix[k] = j+1; //j+1表示公共后缀子串在b[0, i]中的起始下标
            }
            if (j == -1) prefix[k] = true; //如果公共后缀子串也是模式串的前缀子串
        }
    }

    /**
     * 根据好后缀规则，返回滑动的距离
     * @param j 坏字符对应的模式串中的字符下标
     * @param m 模式串长度
     * @param suffix suffix
     * @param prefix prefix
     * @return 滑动距离
     */
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - 1 - j; // 好后缀长度
        // 存在与好后缀匹配的子串
        if (suffix[k] != -1) return j - suffix[k] +1;
        // 存在与好后缀的后缀子串匹配的前缀子串
        for (int r = j+2; r <= m-1; ++r) {
            if (prefix[m-r] == true) {
                return r;
            }
        }
        // 上面两种均不符合，直接滑动m位，即模式串的长度
        return m;
    }

    public static void main(String[] args) {
        ApBoyerMoore apBoyerMoore = new ApBoyerMoore();
        String masterStr = "ABBDNNDIL";
        String patternStr = "ND";
        System.out.println(apBoyerMoore.bm(masterStr, patternStr));
    }
}