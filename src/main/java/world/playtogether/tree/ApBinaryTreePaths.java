package world.playtogether.tree;

import java.util.ArrayList;
import java.util.List;

public class ApBinaryTreePaths {
    List<String> res = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return res;
        traverse(root, String.valueOf(root.val));
        return res;
    }

    void traverse(TreeNode root, String sb) {
        if (root.left == null && root.right == null) {
            res.add(sb);
            return;
        }
        if (root.left != null) {
            traverse(root.left, sb + "->" + root.left.val);
        }
        if (root.right != null) {
            traverse(root.right, sb + "->" + root.right.val);
        }
    }

    static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
}
