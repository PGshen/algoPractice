package world.playtogether.tree;

/**
 * <project> algoPractice
 *
 * <p> 最大二叉树
 *
 * @author penggs
 * @since 2021-02-26
 */
public class ApConstructMaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length-1);
    }

    public TreeNode construct(int[] nums, int low, int high) {
        if (low > high) {
            return null;
        }
        // 找到最大值的下标
        int maxValue = Integer.MIN_VALUE;
        int index = low;
        for (int i = low; i <= high; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                index = i;
            }
        }

        TreeNode treeNode = new TreeNode(maxValue);
        treeNode.left = construct(nums, low, index-1);
        treeNode.right = construct(nums, index + 1, high);
        return treeNode;
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}