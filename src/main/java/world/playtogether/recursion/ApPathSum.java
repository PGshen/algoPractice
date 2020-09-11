package world.playtogether.recursion;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <project> algoPractice
 *
 * <p> 路径和
 *
 * @author penggs
 * @since 2020-09-11
 */
public class ApPathSum {
    Queue<TreeNode> queue = new ArrayDeque<>();
    /**
     * 给一课二叉树，和一个目标值，节点上的值有正有负，返回树中和等于目标值的路径条数
     * @param root
     * @param sum
     * @return
     */
    int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        int pathImLeading = count(root, sum); // 自己为开头的路径数
        int leftPathSum = pathSum(root.left, sum); // 左边路径总数（相信他能算出来）
        int rightPathSum = pathSum(root.right, sum); // 右边路径总数（相信他能算出来）
        return leftPathSum + rightPathSum + pathImLeading;
    }
    int count(TreeNode node, int sum) {
        if (node == null || node.val == null) return 0;
        // 单独节点是否刚好
        int curCount = (node.val == sum) ? 1 : 0;
        // 左子树
        int leftCount = count(node.left, sum - node.val);
        // 右子树
        int rightCount = count(node.right, sum - node.val);
        return  curCount + leftCount + rightCount;
    }

    TreeNode arrToTreeNode(Integer[] arr) {
        TreeNode root = new TreeNode(arr[0]);
        queue.clear();
        queue.offer(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.val == null) continue;
            if (i < arr.length) {
                cur.left = new TreeNode(arr[i++]);
                queue.offer(cur.left);
            }
            if (i < arr.length) {
                cur.right = new TreeNode(arr[i++]);
                queue.offer(cur.right);
            }
        }
        return root;
    }

    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode(Integer val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ApPathSum apPathSum = new ApPathSum();
        Integer[] arr = new Integer[] {10,5,-3,3,2,null,11,3,-2,null,1};
        TreeNode treeNode = apPathSum.arrToTreeNode(arr);
        System.out.println(apPathSum.pathSum(treeNode, 8));
    }
}