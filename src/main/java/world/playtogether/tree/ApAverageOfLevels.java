package world.playtogether.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <project> algoPractice
 *
 * <p> 二叉树的层平均值
 *
 * @author penggs
 * @since 2021-03-07
 */
public class ApAverageOfLevels {
    List<Double> ret = new ArrayList<>();

    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return ret;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> nodes = new ArrayList<>();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ret.add(sum/size);
        }
        return ret;
    }
}