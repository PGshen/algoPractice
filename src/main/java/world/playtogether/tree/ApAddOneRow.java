package world.playtogether.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <project> algoPractice
 *
 * <p> 给指定深度的层次增加一行
 *
 * @author penggs
 * @since 2021-03-07
 */
public class ApAddOneRow {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        // 特殊情况，只有1层
        if (d == 1) {
            TreeNode treeNode = new TreeNode(v);
            treeNode.left = root;
            return treeNode;
        }
        // 层次遍历，找到第d层
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> nodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                nodes.add(queue.poll());
            }
            if (level == d - 1) {
                nodes.forEach(node -> {
                    TreeNode xLeftNode = new TreeNode(v);
                    xLeftNode.left = node.left;
                    node.left = xLeftNode;
                    TreeNode xRightNode = new TreeNode(v);
                    xRightNode.right = node.right;
                    node.right = xRightNode;
                });
                break;
            } else {
                level++;
                nodes.forEach(node -> {
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                });
            }
        }
        return root;
    }
}