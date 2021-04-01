package world.playtogether.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 恢复二叉搜索树
 *
 * @author penggs
 * @since 2021-03-07
 */
public class ApRecoverTree {
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    TreeNode err1, err2 = null;

    public void recoverTree(TreeNode root) {
        inOrder(root);
        // 将错位的节点的值进行交换
        int temp=err1.val;
        err1.val=err2.val;
        err2.val=temp;
    }

    // 通过中序遍历找到错位的节点
    void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        // 通过err1是否为null来判断是否第一个
        if (prev.val > root.val && err1 == null) {
            // 第一个顺序错误的地方，记录的节点是前节点
            err1 = prev;
        }
        if (prev.val > root.val) {
            // 第二个错位的地方，注意记录的节点是后节点
            err2 = root;
        }
        prev = root;
        inOrder(root.right);
    }

    /**
     * 有序数组转平衡二叉树
     * @param nums 有序数组
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return toBst(nums, 0, nums.length-1);
    }

    TreeNode toBst(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (start + end)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = toBst(nums, start, mid-1);
        root.right = toBst(nums, mid+1, end);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5};
        TreeNode node = new ApRecoverTree().sortedArrayToBST(nums);
        List<Integer> list = new ArrayList<>();
        list.remove(list.size()-1);
        System.out.println("");
    }
}