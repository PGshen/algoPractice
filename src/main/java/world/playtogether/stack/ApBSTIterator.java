package world.playtogether.stack;

import world.playtogether.tree.TreeNode;

import java.util.Stack;

/**
 * <project> algoPractice
 *
 * <p> 实现二叉搜索树的升序迭代器
 * 实现思路：中序遍历的迭代实现，单调栈。左节点先入栈，一路向左，直到没有左节点，开始弹出栈顶。弹出栈顶后右节点入栈，然后重复前面的步骤。最终的弹出顺序即为中序遍历的顺序
 *
 * @author penggs
 * @since 2021-04-01
 */
public class ApBSTIterator {
    Stack<TreeNode> stack = new Stack<>();

    // 初始化先入栈一部分
    public ApBSTIterator(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    // 每次出栈的时候，将右节点及其左节点入栈
    public int next() {
        TreeNode node = stack.pop();
        if (node.right != null) {
            // 从右节点开始，一路先向左入站
            TreeNode p = node.right;
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
        }
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}