package world.playtogether.hot;

import java.util.LinkedList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 括号生成
 * 输入是一个正整数 n，输出是 n 对儿括号的所有合法组合
 * 回溯
 *
 * @author penggs
 * @since 2020-08-22
 */
public class ApGenParenthesis {

    /**
     * 生成括号
     *
     * @param n n对括号
     * @return java.util.List<java.util.List<java.lang.Character>>
     * @author penggs
     * @since 2020/8/22
     */
    List<List<Character>> genParenthesis(int n) {
        List<List<Character>> res = new LinkedList<>();
        LinkedList<Character> track = new LinkedList<>();
        if (n==0) return res;
        backtrack(n, n, track, res);
        return res;

    }

    /**
     * 回溯
     *
     * @param left 左括号剩余个数
	 * @param right 右括号剩余个数
	 * @param res 结果集
     * @author penggs
     * @since 2020/8/22
     */
    void backtrack(int left, int right, LinkedList<Character> track, List<List<Character>> res) {
        // 左比右还多，不符合，剪枝
        if (left > right) return;
        if (left < 0) return;
        // 左右均剩余零个，匹配完成
        if (left == 0 && right == 0) {
            res.add(new LinkedList<>(track));
            return;
        }

        // 放入左括号
        track.addLast('(');
        backtrack(left-1, right, track, res);
        // 撤回
        track.removeLast();

        // 放入右括号
        track.addLast(')');
        backtrack(left, right-1, track, res);
        // 撤回
        track.removeLast();
    }

    public static void main(String[] args) {
        ApGenParenthesis apGenParenthesis = new ApGenParenthesis();
        apGenParenthesis.genParenthesis(3).forEach(System.out::println);
    }
}