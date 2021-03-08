package world.playtogether.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <project> algoPractice
 *
 * <p> 二叉树的莫里斯遍历
 * 利用叶子节点的空闲指针标记前驱节点，再第二次访问时将其重置断开
 *
 * @author penggs
 * @since 2021-03-08
 */
public class ApMorrisTraverse {
    // 莫里斯先序遍历
    static List<Integer> morrisPreOrderTraverse(TreeNode head) {
        List<Integer> ret = new ArrayList<>();
        if (head == null) return ret;
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            // 存在左子树
            if (mostRight != null) {
                // 找到左子树的最右节点
                while (mostRight.right != null && mostRight.right != cur) mostRight = mostRight.right;
                if (mostRight.right == null) {
                    // 第一次访问cur时,mostRight.right为NULL。此时将最右节点指向cur节点，以便于访问到这个节点的时候能回到cur
                    mostRight.right = cur;
                    ret.add(cur.val);
                    cur = cur.left;     // 向左移动
                } else {
                    // 第二次访问cur时，mostRight.right是指向cur的.此时要断开[真]叶子节点的右指针
                    mostRight.right = null;
                    cur = cur.right;    // 到这里说明左子树均被访问过了，所以这里向右移动
                }
            } else {
                // 不存在左子树
                ret.add(cur.val);
                cur = cur.right;    // 向右移动
            }
        }
        return ret;
    }

    // 莫里斯中序遍历
    static List<Integer> morrisInOrderTraverse(TreeNode head) {
        List<Integer> ret = new ArrayList<>();
        if (head == null) return ret;
        TreeNode cur = head;
        TreeNode mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            // 存在左子树
            if (mostRight != null) {
                // 找到左子树的最右节点
                while (mostRight.right != null && mostRight.right != cur) mostRight = mostRight.right;
                if (mostRight.right == null) {
                    // 第一次访问cur时,mostRight.right为NULL。此时将最右节点指向cur节点，以便于访问到这个节点的时候能回到cur
                    mostRight.right = cur;
                    cur = cur.left;     // 向左移动
                } else {
                    // 第二次访问cur时，mostRight.right是指向cur的.此时要断开[真]叶子节点的右指针
                    mostRight.right = null;
                    ret.add(cur.val);   // 准备转向右子树了
                    cur = cur.right;    // 到这里说明左子树均被访问过了，所以这里向右移动
                }
            } else {
                // 不存在左子树
                ret.add(cur.val);
                cur = cur.right;    // 向右移动
            }
        }
        return ret;
    }

    // 莫里斯先后序遍历
    // 总体思路是，向访问右子树的节点，最后再反转
    static List<Integer> morrisPostOrderTraverse(TreeNode head) {
        List<Integer> ret = new ArrayList<>();
        if (head == null) return ret;
        TreeNode cur = head;
        TreeNode mostLeft = null;
        // 先访问右子树的节点
        while (cur != null) {
            mostLeft = cur.right;
            // 存在右子树
            if (mostLeft != null) {
                // 找到右子树的最左节点
                while (mostLeft.left != null && mostLeft.left != cur) mostLeft = mostLeft.left;
                if (mostLeft.left == null) {
                    // 第一次访问cur时,mostLeft.left为NULL。此时将最左节点指向cur节点，以便于访问到这个节点的时候能回到cur
                    mostLeft.left = cur;
                    ret.add(cur.val);
                    cur = cur.right;     // 向右移动
                } else {
                    // 第二次访问cur时，mostLeft.left是指向cur的.此时要断开[真]叶子节点的左指针
                    mostLeft.left = null;
                    cur = cur.left;    // 到这里说明右子树均被访问过了，所以这里向左移动
                }
            } else {
                // 不存在右子树
                ret.add(cur.val);
                cur = cur.left;    // 向左移动
            }
        }
        // 最后在反转
        Collections.reverse(ret);
        return ret;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1, new TreeNode(2, null, new TreeNode(3)), new TreeNode(4, new TreeNode(5), null));
        System.out.println(morrisPreOrderTraverse(node).toString());
        System.out.println(morrisInOrderTraverse(node).toString());
        System.out.println(morrisPostOrderTraverse(node).toString());
    }
}