package world.playtogether.tree;

/**
 * <project> algoPractice
 *
 * <p> 完全二叉树
 *
 * @author penggs
 * @since 2020-09-20
 */
public class ApCompleteBiTreeOp<T> {

    /**
     * 计算完全二叉树的节点数
     * 时间复杂度：O(logN * logN)
     * @param root
     * @return
     */
    public int countNodes(BiNode<T> root) {
        BiNode<T> left = root, right = root;
        int leftHigh = 0, rightHigh = 0;
        // 左子树高度
        while (left != null) {
            leftHigh++;
            left = left.left;
        }
        // 右子树高度
        while (right != null) {
            rightHigh++;
            right = right.right;
        }
        // 左右子树一样高，节点数为2^h - 1
        if (leftHigh == rightHigh) {
            return (int)Math.pow(2, leftHigh) - 1;
        }
        // 左右子树不一样高,按普通二叉树计算。此处只有一个子树会进入递归
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static void main(String[] args) {
        ApBiTreeSerialization<String> serialization = new ApBiTreeSerialization<>();
        String sb = "1,2,4,#,#,#,3,#,#";
        BiNode<String> node = serialization.deserialize(sb);
        ApCompleteBiTreeOp<String> completeBiTreeOp = new ApCompleteBiTreeOp<>();
        System.out.println(completeBiTreeOp.countNodes(node));
    }
}