package world.playtogether.hot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <project> algoPractice
 *
 * <p> 从前序与中序遍历序列构造二叉树
 *
 * @author penggs
 * @since 2020-12-09
 */
public class ApBuildTreeByPreInOrder {
    /**
     * 前序遍历的第一位就是根节点，确定根节点后，找到这个节点在中序遍历序列的位置，位于该位置左侧的序列为左子树的节点，右侧为右子树。然后递归的处理
     * 时间复杂度(n^2)
     * @param preorder 前序序列
     * @param inorder 后序序列
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        //根据前序数组的第一个元素，就可以确定根节点
        TreeNode root = new TreeNode(preorder[0]);
        for (int i = 0; i < preorder.length; ++i) {
            //用preorder[0]去中序数组中查找对应的元素
            if (preorder[0] == inorder[i]) {
                //将前序数组分成左右两半，再将中序数组分成左右两半
                //之后递归的处理前序数组的左边部分和中序数组的左边部分
                //递归处理前序数组右边部分和中序数组右边部分
                int[] pre_left = Arrays.copyOfRange(preorder, 1, i + 1);    // 第一位已用掉
                int[] pre_right = Arrays.copyOfRange(preorder, i + 1, preorder.length);
                int[] in_left = Arrays.copyOfRange(inorder, 0, i);  // 中间位已用掉
                int[] in_right = Arrays.copyOfRange(inorder, i + 1, inorder.length);
                root.left = buildTree(pre_left, in_left);
                root.right = buildTree(pre_right, in_right);
                break;
            }
        }
        return root;
    }

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
    }

    // 前序+中序
    public static TreeNode build(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        // 结束条件
        if (ps > pe) return null;
        // 查找前序的第一个元素在中序的位置
        int index = is;
        for (int i = is; i <= ie; i++) {
            if (preorder[ps] == inorder[i]) {
                index = i;
                break;
            }
        }
        // 找到位置的左侧是左子树，右侧是柚子树，计算得到长度后面用于分界
        int len = index - is;
        TreeNode treeNode = new TreeNode(preorder[ps]);
        // 根据index下标和长度找到分界点
        treeNode.left = build(preorder, ps + 1, ps + len, inorder, is, index - 1);
        treeNode.right = build(preorder, ps + len + 1, pe, inorder, index + 1, ie);
        return treeNode;
    }

    // 后序遍历+中序
    public static TreeNode build2(int[] postorder, int ps, int pe, int[] inorder, int is, int ie) {
        // 结束条件
        if (ps > pe) return null;
        // 查找后序的最后一个元素在中序的位置
        int index = is;
        for (int i = is; i <= ie; i++) {
            if (postorder[pe] == inorder[i]) {
                index = i;
                break;
            }
        }
        // 找到位置的左侧是左子树，右侧是柚子树，计算得到长度后面用于分界
        int len = index - is;
        TreeNode treeNode = new TreeNode(postorder[pe]);
        // 根据index下标和长度找到分界点.边界值的确定可也一个实例去推算
        treeNode.left = build(postorder, ps, ps + len - 1, inorder, is, index - 1);
        treeNode.right = build(postorder, ps + len, pe - 1, inorder, index + 1, ie);
        return treeNode;
    }



    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    (left != null ? ", left=" + left.toString() : "") +
                    (right != null ?  ", right=" + right.toString() : "") +
                    '}';
        }
    }

    public void flatten(TreeNode root) {
        if (root == null) return;
        List<Integer> values = new ArrayList<>();
        flat(root, values);
        root = new TreeNode(values.get(values.size() - 1));
        for (int i = values.size() - 2; i >= 0; i--) {
            root = new TreeNode(values.get(i),null, root);
        }
    }

    private void flat(TreeNode root, List<Integer> values) {
        if (root == null) return;
        values.add(root.val);
        flat(root.left, values);
        flat(root.right, values);
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        System.out.println(buildTree(preorder, inorder));
    }
}