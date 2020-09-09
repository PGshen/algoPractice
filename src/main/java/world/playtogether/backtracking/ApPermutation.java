package world.playtogether.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
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
            res.add(new LinkedList<>(track));
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

    /**
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        res.clear();
        LinkedList<Integer> track = new LinkedList<>();
        // 记录已访问过的索引
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, visited, track);
        return res;
    }

    /**
     * 回溯，画出递归树，判断哪些可以剪枝的
     * @param nums
     * @param visited
     * @param track
     */
    void backtrack(int[] nums, boolean[] visited, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new ArrayList<>(track));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            // 当前值和前面的值一样，剪枝
            if(i>0 && nums[i] == nums[i-1] && visited[i-1]) break;
            visited[i] = true;
            track.addLast(nums[i]);
            backtrack(nums, visited, track);
            visited[i] = false;
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        ApPermutation apPermutation = new ApPermutation();
        int[] nums = new int[] {1, 2, 3};
        apPermutation.permute(nums).forEach(System.out::println);
        System.out.println();
        int[] nums2 = new int[] {1, 1, 3};
        apPermutation.permuteUnique(nums2).forEach(System.out::println);

    }
}