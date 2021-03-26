package world.playtogether.hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ApCombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, track, res);
        return res;
    }

    void backtrack(int[] candidates, int target, int i, LinkedList<Integer> track, List<List<Integer>> res) {
        if (target == 0){
            res.add(new ArrayList<>(track));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int j = i; j < candidates.length; j++) {
            if (candidates[j] > target) break;  // 后续值已超出，提前退出，剪枝
            if (j > i && candidates[j] == candidates[j-1]) continue;    // 一种特殊情况，因为相同的值可能会导致结果集重复，这里排除
            // 选择
            track.add(candidates[j]);
            // 递归
            backtrack(candidates, target - candidates[j], j+1, track, res);
            // 撤销
            track.removeLast();
        }
    }
}
