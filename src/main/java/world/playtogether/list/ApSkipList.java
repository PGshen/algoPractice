package world.playtogether.list;

import java.util.Random;

/**
 * <project> algoPractice
 *
 * <p> 跳表
 *
 * @author penggs
 * @since 2020-08-10 16:44
 */
@SuppressWarnings("unchecked")
public class ApSkipList<T extends Comparable<T>> {
    private static final int MAX_LEVEL = 16;
    private int levelCount = 1;

    // 带头节点
    private final Node<T> head = new Node<>(MAX_LEVEL);
    private final Random random = new Random();

    /**
     * 搜索
     *
     * @param data 目标值
     * @return world.playtogether.list.ApSkipList.Node<T>
     * @author penggs
     * @since 2020/8/10
     */
    public Node<T> find(T data) {
        Node<T> p = head;
        // 从最高层先尽量向右移动，然后向下移动，通过i--使p指针向下沉
        for (int i = levelCount - 1; i >= 0; i--) {
            // 横向移动p,直到遇到比目标值大的节点，p记录这个节点的前置节点
            while (p.forwards[i] != null && p.forwards[i].getData().compareTo(data) < 0) {
                p = p.forwards[i];
            }
        }
        // 在第0层判断是否与要找的值相等
        if (p.forwards[0] != null && p.forwards[0].getData().equals(data)) {
            return p.forwards[0];
        } else {
            return null;
        }
    }

    /**
     * 插入
     *
     * @param data 值
     * @author penggs
     * @since 2020/8/10
     */
    public void insert(T data) {
        int level = head.forwards[0] == null ? 1 : randomLevel();
        if (level > levelCount) {
            // 每层插入最多增加一层索引，避免过早建立过多的索引层
            level = ++levelCount;
        }
        Node<T> newNode = new Node<>(level);
        newNode.setData(data);
        Node<T> p = head;
        // 从最高层开始，找到插入位置，然后下沉层级
        for (int i = levelCount - 1; i >= 0; i--) {
            // 找到当前层的插入位置的前置节点
            while (p.forwards[i] != null && p.forwards[i].getData().compareTo(data) < 0) {
                p = p.forwards[i];
            }
            // level为本次插入的最高层索引，超过的将不会插入
            if (level > i) {
                // 区分是否最后一个节点
                if (p.forwards[i] == null) {
                    p.forwards[i] = newNode;
                } else {
                    Node<T> next = p.forwards[i];
                    p.forwards[i] = newNode;
                    newNode.forwards[i] = next;
                }
            }
        }
    }

    /**
     * 删除
     *
     * @param data 值
     * @author penggs
     * @since 2020/8/10
     */
    public void delete(T data) {
        Node<T> p = head;
        // 先找到每层待删除节点的前置节点，即为最接近目标值的节点
        // 从最高层向下移动
        for (int i = levelCount - 1; i >= 0; i--) {
            while (p.forwards[i] != null && p.forwards[i].getData().compareTo(data) < 0) {
                // 向右横向移动
                p = p.forwards[i];
            }
            //delPrev[i] = p;
            if (p.forwards[i] != null && p.forwards[i].getData().equals(data)) {
                // 若相等，则删除
                p.forwards[i] = p.forwards[i].forwards[i];
            }
        }
    }

    /**
     * 随机层数
     *
     * @return int
     * @author penggs
     * @since 2020/8/10
     */
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    public void printAll() {
        Node<T>[] c = head.forwards;
        Node<T>[] d = c;
        for (int i = levelCount - 1; i >= 0; i--) {
            System.out.print("L" + i + ": ");
            do {
                System.out.print((d[i] != null ? d[i].data : null) + "---");
            } while (d[i] != null && (d = d[i].forwards)[i] != null);
            System.out.println();
            d = c;
        }
        System.out.println();
    }

    @SuppressWarnings("unchecked")
    static class Node<T> {
        private T data;
        private final Node<T>[] forwards;

        Node(int level) {
            forwards = new Node[level];
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data.toString() +
                    '}';
        }
    }

    public static void main(String[] args) {
        ApSkipList<Integer> skipList = new ApSkipList<>();
        skipList.insert(1);
        skipList.insert(2);
        skipList.insert(6);
        skipList.insert(7);
        skipList.insert(8);
        skipList.insert(3);
        skipList.insert(4);
        skipList.insert(5);
        skipList.printAll();
        skipList.delete(5);
        skipList.delete(4);
        skipList.printAll();
        skipList.insert(5);
        skipList.printAll();
        System.out.println(skipList.find(2));
        System.out.println(skipList.find(22));
    }
}