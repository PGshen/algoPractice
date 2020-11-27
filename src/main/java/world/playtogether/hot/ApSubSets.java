package world.playtogether.hot;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 子集
 *
 * @author penggs
 * @since 2020-11-27
 */
public class ApSubSets {
    /**
     * 回溯法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        res.add(track);
        for(int k = 1; k <= nums.length; k++) {
            backtrack(nums, k, 0, track, res);
        }
        return res;
    }

    // size 本次子集的长度， begin 本次递归的起始索引，目的是为了不重复选择已选择过的的值
    void backtrack(int[] nums, int size, int begin, List<Integer> track, List<List<Integer>> res) {
        // 满足条件，加入结果集
        if (track.size() == size) {
            res.add(new ArrayList(track));
            return;
        }
        for(int i = begin; i < nums.length; i++) {
            // 选择
            track.add(nums[i]);
            // 递归
            backtrack(nums, size, i + 1, track, res);
            // 撤销选择
            track.remove(track.size() - 1);
        }
    }
}