package world.playtogether.charmatch;

import java.util.*;

/**
 * <project> algoPractice
 *
 * <p> AC自动机
 * Trie 树构建的时间复杂度是 O(m*len)，其中 len 表示敏感词的平均长度，m 表示敏感词的个数。
 * 整个失败指针的构建过程就是 O(k*len),其中 k是 Trie 树中总的节点个数
 * 匹配的时间复杂度就是 O(n*len)
 *
 * @author penggs
 * @since 2020-08-17
 */
public class ApAcAutoMata {

    private AcNode root;

    public ApAcAutoMata() {
        root = new AcNode("/");
    }

    /**
     * 构建字典树
     *
     * @param pattern 模式串
     * @author penggs
     * @since 2020/8/17
     */
    public void insert(String pattern) {
        AcNode p = this.root;
        int len = pattern.length();
        for (int i = 0; i < len; i++) {
            String ch = pattern.charAt(i) + "";
            if (p.children.get(ch) == null) {
                p.children.put(ch, new AcNode(ch));
            }
            p = p.children.get(ch);
        }
        p.isEnding = true;
        p.length = pattern.length();
    }

    /** 
     * 构建失败指针
     * 失败指针指向那个最长匹配后缀子串对应的模式串的前缀的最后一个节点
     * （画图理解）
     * 
     * @author penggs
     * @since 2020/8/17 
     */
    public void buildFailurePointer() {
        AcNode root = this.root;
        Queue<AcNode> queue = new LinkedList<>();
        queue.offer(root);
        // 层次遍历实现失败指针指向
        while (!queue.isEmpty()) {
            AcNode p = queue.poll();
            for (AcNode pc: p.children.values()) {
                if (pc == null) {
                    continue;
                }
                if (p == root) {
                    // 如果p以移动到root，那将失败指针指向root
                    pc.fail = root;
                } else {
                    AcNode q = p.fail;
                    while (q != null) {
                        // 找到与pc.data相等的子节点
                        AcNode qc = q.children.get(pc.data);
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.offer(pc);
            }
        }
    }

    public List<Integer> match(String text) {
        List<Integer> positions = new ArrayList<>();
        AcNode root = this.root;
        AcNode p = root;
        for (int i = 0; i < text.length(); i++) {
            String ch = text.charAt(i) + "";
            // 失败指针发挥作用的地方
            while (p.children.get(ch) == null && p != root) {
                p = p.fail;
            }
            p = p.children.get(ch);
            if (p == null) {
                // 如果没有匹配的，从root开始重新匹配
                p = root;
            }

            AcNode tmp = p;
            while (tmp != root) {
                // 失败指针所经过的节点，通过判断是否叶子节点判断是否是模式串
                if (tmp.isEnding) {
                    positions.add(i - p.length + 1);
                    //return true;
                }
                tmp = tmp.fail;
            }
        }
        return positions;
    }

    /**
     * 匹配
     *
     * @param text 主串
	 * @param patterns 模式串组
     * @return java.util.List<java.lang.Integer>
     * @author penggs
     * @since 2020/8/17
     */
    public static List<Integer> match(String text, String[] patterns) {
        ApAcAutoMata autoMata = new ApAcAutoMata();
        for (String pattern: patterns) {
            // 构建字典树
            autoMata.insert(pattern);
        }
        // 构建失败指针
        autoMata.buildFailurePointer();
        return autoMata.match(text);
    }


    public static class AcNode {
        String data;
        Map<String, AcNode> children;
        Boolean isEnding;
        Integer length;
        AcNode fail;

        public AcNode(String data) {
            this.data = data;
            this.children = new HashMap<>();
            this.isEnding = false;
            this.length = 0;
            this.fail = null;
        }
    }

    public static void main(String[] args) {
        String[] patterns = {"at", "art", "oars", "soar"};
        String text = "soarsoars";
        System.out.println(match(text, patterns));

        String[] patterns2 = {"Fxtec Pro", "谷歌Pixel", "Fxtex"};

        String text2 = "一家总部位于伦敦的公司Fxtex在MWC上就推出了一款名为Fxtec Pro1的手机，该机最大的亮点就是采用了侧滑式全键盘设计。DxOMark年度总榜发布 华为P20 Pro/谷歌Pixel 3争冠";
        System.out.println(match(text2, patterns2));
    }
}