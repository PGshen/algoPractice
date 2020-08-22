package world.playtogether.backtracking;

import java.util.LinkedList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 全排列
 *
 * @author penggs
 * @since 2020-08-22
 */
public class ApPermutation {
    List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输入一组不重复的数字，返回它们的全排列 */
    List<List<Integer>> permute(int[] nums) {
        res.clear();
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素
    // 结束条件：nums 中的元素全都在 track 中出现
    void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            // new LinkedList() 新建一个对象
            res.add(new LinkedList(track));
            return;
        }

        for (int num : nums) {
            // 排除不合法的选择
            if (track.contains(num))
                continue;
            // 做选择
            track.add(num);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        ApPermutation apPermutation = new ApPermutation();
        int[] nums = new int[] {1, 2, 3};
        apPermutation.permute(nums).forEach(System.out::println);
    }
}