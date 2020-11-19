package world.playtogether.hot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 全排列
 *
 * @author penggs
 * @since 2020-11-19
 */
public class ApPermutation {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track, res);
        return res;
    }

    static void backtrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for(int num: nums) {
            // 排除自身
            if(track.contains(num)) continue;
            // 选择
            track.addLast(num);
            backtrack(nums, track, res);
            // 撤销选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3}));
    }
}