package world.playtogether.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 用矩阵的格式输出二叉树
 *
 * @author penggs
 * @since 2021-03-07
 */
public class ApPrintTree {
    List<List<String>> ans = new ArrayList<>();

    public List<List<String>> printTree(TreeNode root) {
        int depth = getDepth(root);
        int width = (int) (Math.pow(2, depth) - 1);
        List<String> ansLevel = new ArrayList<>(width);
        for (int i = 0; i < width; i++) {
            ansLevel.add("");
        }
        dfs(root, ansLevel, 1, 0, width - 1);
        return ans;
    }

    // 深度遍历填充
    void dfs(TreeNode root, List<String> ansLevel, int depth, int low, int high) {
        if (root == null) return;
        if(ans.size()<depth) ans.add(new ArrayList<>(ansLevel));    // 没被初始化过，新加一行
        int index = (low + high)/2;     // 二分的方式确定当前值的位置
        ans.get(depth-1).set(index, String.valueOf(root.val));      // 获取到depth-1行，然后设置找到的位置
        dfs(root.left, ansLevel, depth + 1, low, index - 1);
        dfs(root.right, ansLevel, depth + 1, index + 1, high);
    }

    // 获取树的深度
    int getDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }
}