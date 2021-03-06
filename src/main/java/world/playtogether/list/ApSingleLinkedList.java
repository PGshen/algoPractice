package world.playtogether.list;

/**
 * algoPractice
 *
 * <p> 单向链表,(带头节点)
 *
 * @author : penggs
 * @since : 2020-08-05 23:13
 */
public class ApSingleLinkedList<T> {

    private int size = 0;
    private Node<T> head = null;

    public int getSize() {
        return size;
    }

    /**
     * 通过索引查找值
     *
     * @param index 索引
     * @return world.playtogether.list.ApSingleLinkedList.Node<T>
     * @author penggs
     * @since 2020/8/6
     */
    public Node<T> findByIndex(int index) {
        Node<T> p = head;
        int pos = 0;
        while (p != null && pos != index) {
            pos++;
            p = p.next;
        }
        if (pos == index) {
            return p;
        } else {
            throw new IllegalArgumentException("index illegal!");
        }
    }

    /**
     * 通过值查找节点，只返回查找到的第一个节点
     *
     * @param data 值
     * @return world.playtogether.list.ApSingleLinkedList.Node<T>
     * @author penggs
     * @since 2020/8/6
     */
    public Node<T> findByData(T data) {
        Node<T> p = head;
        while (p != null) {
            if (p.data.equals(data)) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 是否存在这个节点
     *
     * @param node 节点
     * @return boolean
     * @author penggs
     * @since 2020/8/6
     */
    public boolean existNode(Node<T> node) {
        Node<T> p = head;
        while (p != null && p !=node) {
            p = p.next;
        }
        return p != null;
    }

    public void insertToHead(T data) {
        Node<T> newNode = new Node<>(data, null);
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    public void insertToTail(T data) {
        Node<T> newNode = new Node<>(data, null);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> p =head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = newNode;
        }
        size++;
    }

    /**
     * 插入到指定值后
     *
     * @param data 要插入的值
	 * @param afterData 指定值
     * @author penggs
     * @since 2020/8/6
     */
    public void insertAfter(T afterData, T data) {
        Node<T> afterNode = findByData(afterData);
        insertAfter(afterNode, data);
    }

    public void insertAfter(Node<T> afterNode, T data) {
        if (afterNode == null) {
            throw new IllegalArgumentException("afterNode must not be null!");
        }
        if (!existNode(afterNode)) {
            throw new IllegalArgumentException("afterNode does not exist!");
        }
        Node<T> newNode = new Node<>(data, null);
        newNode.next = afterNode.next;
        afterNode.next = newNode;
        size++;
    }

    /**
     * 插入到指定值前
     *
     * @param beforeData 指定值
	 * @param data 插入值
     * @author penggs
     * @since 2020/8/6
     */
    public void insertBefore(T beforeData, T data) {
        Node<T> beforeNode = findByData(beforeData);
        insertBefore(beforeNode, data);
    }

    public void insertBefore(Node<T> beforeNode, T data) {
        if (beforeNode == null) {
            throw new IllegalArgumentException("beforeNode must not be null");
        }
        if (beforeNode == head) {
            insertToHead(data);
            return;
        }
        // 找到beforeNode节点的前置节点
        Node<T> p = head;
        while (p != null && p.next != beforeNode) {
            p = p.next;
        }
        if (p == null) {
            throw new IllegalArgumentException("beforeNode does not exist!");
        }
        Node<T> newNode = new Node<>(data, null);
        newNode.next = beforeNode;
        p.next = newNode;
        size++;
    }

    /**
     * 删除指定节点
     *
     * @param delNode 节点
     * @author penggs
     * @since 2020/8/7
     */
    public void deleteByNode(Node<T> delNode) {
        if (delNode == null || head == null) {
            return;
        }
        if (delNode == head) {
            head = head.next;
            size--;
            return;
        }
        // 找到要删除节点的前置节点
        Node<T> p = head;
        while (p != null && p.next != delNode) {
            p = p.next;
        }
        // 未找到
        if (p == null) {
            throw new IllegalArgumentException("delNode does not exist!");
        }
        p.next = p.next.next;
        size--;
    }

    public void deleteByData(T data) {
        Node<T> delNode = findByData(data);
        if (delNode == null) {
            throw new IllegalArgumentException("data not found!");
        }
        deleteByNode(delNode);
    }

    /**
     * 删除尾节点
     *
     * @author penggs
     * @since 2020/8/8
     */
    public void deleteTail() {
        // 无节点
        if (head == null) {
            return;
            // 只有一个节点
        } else if (head.next == null) {
            head = null;
            size--;
            return;
        }
        Node<T> p = head;
        // 找到尾节点的前置节点
        while (p.next.next != null) {
            p = p.next;
        }
        p.next = null;
        size--;
    }

    /**
     * 删除第一个节点，非head节点
     *
     * @author penggs
     * @since 2020/8/8
     */
    public void deleteHead() {
        if (head == null) {
            return;
        } else if (head.next == null) {
            head = null;
            size--;
            return;
        }
        Node<T> p = head.next;
        head.next = null;
        head = p;
        size++;
    }

    public Node<T> getHead() {
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> p = head;
        while (p != null) {
            sb.append(p.toString());
            p = p.next;
            if (p != null) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 节点
     */
    public static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node<T> getNext() {
            return next;
        }

        public T getData() {
            return data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public static void main(String[] args) {
        ApSingleLinkedList<String> linkedList = new ApSingleLinkedList<>();
        linkedList.insertToHead("One");
        linkedList.insertToTail("Two");
        linkedList.insertBefore("One", "BeforeOne");
        linkedList.insertAfter("Two", "AfterTwo");
        Node<String> node = linkedList.findByData("One");
        linkedList.insertBefore(node, "BeforeOne2");
        linkedList.insertAfter(node, "AfterOne2");
        System.out.println(linkedList.toString());
        System.out.println(linkedList.existNode(node));
        linkedList.deleteByNode(node);
        System.out.println(linkedList.toString());
        linkedList.deleteByData("Two");
        System.out.println(linkedList.toString());
        linkedList.insertToHead("Head");
        System.out.println(linkedList.toString());
        System.out.println(linkedList.findByIndex(2));
        System.out.println(linkedList.findByData("Three"));
        System.out.println(linkedList.getSize());
        System.out.println(linkedList.findByIndex(20));
    }
}