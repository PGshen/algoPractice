package world.playtogether.tree;

import java.util.LinkedList;

/**
 * <project> algoPractice
 *
 * <p> 二叉树序列化和反序列化
 *
 * @author penggs
 * @since 2020-09-13
 */
public class ApBiTreeSerialization<T> {
    // 分隔符和空指针
    private static final String SEP = ",";
    private static final String NULL = "#";

    /**
     * 序列化方法，前序遍历方式
     * @param root
     * @return
     */
    public String serialize(BiNode<T> root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    /**
     * 反序列化方法，前序遍历方式
     * @param data
     * @return
     */
    public BiNode<T> deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            // s如果是对象，就需要对该对象进行反序列化
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    void serialize(BiNode<T> root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.data).append(SEP);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    BiNode<T> deserialize(LinkedList<String> nodes) {
        if (nodes.isEmpty()) return null;
        String first = nodes.removeFirst();
        if (first.equals(NULL)) return null;
        BiNode<T> root = new BiNode(first);
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }

    public static void main(String[] args) {
        ApBiTreeSerialization<String> serialization = new ApBiTreeSerialization<>();
        String sb = "1,2,#,4,#,#,3,#,#,";
        BiNode<String> node = serialization.deserialize(sb);
        System.out.println(serialization.serialize(node));
    }
}