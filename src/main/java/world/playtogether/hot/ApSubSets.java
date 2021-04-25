package world.playtogether.hot;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * 归纳法
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        if (nums.length == 0) {
            // 如果为空，返回一个空集合
            List<List<Integer>> res = new ArrayList<>();
            res.add(new ArrayList<>());
            return res;
        }
        // 取出数组的最后一个值
        int n = nums[nums.length-1];
        nums = Arrays.copyOf(nums, nums.length-1);
        // 递归剩余的
        List<List<Integer>> res = subsets2(nums);

        int size = res.size();
        for (int i = 0; i < size; i++) {
            // 复制一份添加到结果集的后面，然后在添加元素
            res.add(new ArrayList<>(res.get(i)));
            res.get(res.size()-1).add(n);
        }
        return res;
    }

    /**
     * 回溯
     */
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets3(int[] nums) {
        backtrack2(nums, 0,new ArrayList<>());
        return res;
    }

    void backtrack2(int[] nums, int begin, List<Integer> track) {
        res.add(new ArrayList<>(track));
        // 从begin开始选择，避免选到之前选过的
        for (int i = begin; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack2(nums, i+1, track);
            track.remove(track.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        //System.out.println(new ApSubSets().subsets2(nums));
        System.out.println(new ApSubSets().subsets3(nums));
    }
}