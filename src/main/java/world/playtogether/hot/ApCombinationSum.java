package world.playtogether.hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 组合总数
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author penggs
 * @since 2020-11-17
 */
public class ApCombinationSum {
    /**
     * 回溯法，先画出递归树，判断出剪枝条件。善用排序来去重
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        // 先排序，方便剪枝
        Arrays.sort(candidates);
        backtrack(candidates, target, track, res);
        return res;
    }

    public static void backtrack(int[] candidates, int target, List<Integer> track, List<List<Integer>> res) {
        if (target == 0) {
            // 等于目标值，加入结果集
            res.add(new ArrayList<>(track));
            return;
        }
        if (target < 0) {
            // 小于0，直接剪枝
            return;
        }
        // 选择
        // 递归
        // 撤销
        for (int candidate: candidates) {
            // 剪枝，避免重复
            if (track.size() > 0 && candidate < track.get(track.size() - 1)) continue;
            track.add(candidate);
            backtrack(candidates, target - candidate, track, res);
            track.remove(track.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,6,7};
        System.out.println(combinationSum(nums, 7));
        int[] nums2 = new int[]{2,3,5};
        System.out.println(combinationSum(nums2, 8));
    }
}