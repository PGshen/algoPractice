package world.playtogether.list;

/**
 * <project> algoPractice
 *
 * <p> 双向链表
 *
 * @author penggs
 * @since 2020-08-08 15:32
 */
public class ApDoubleLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public ApDoubleLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    public int getSize() {
        return size;
    }

    public Node<T> findByIndex(int index) {
        if (index < 0 || index > size - 1) {
            // 越界了，返回null
            return null;
        }
        int pos = 0;
        Node<T> p;
        // 靠近头节点
        if (index < size/2) {
            p = head;
            while (p != null && pos != index) {
                pos++;
                p = p.next;
            }
        } else {
            // 靠近尾节点
            p = tail;
            while (p != null && pos != size - 1 - index) {
                pos++;
                p = p.prev;
            }
        }
        return p;
    }

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

    public boolean existNode(Node<T> node) {
        Node<T> p = head;
        while (p != null && p != node) {
            p = p.next;
        }
        return p != null;
    }

    /**
     * 头插
     *
     * @param data data
     * @author penggs
     * @since 2020/8/8
     */
    public void insertToHead(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        newNode.next = head;
        head = newNode;
        size++;
    }

    /**
     * 尾插
     *
     * @param data data
     * @author penggs
     * @since 2020/8/8
     */
    public void insertToTail(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

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
        Node<T> newNode = new Node<>(data);
        newNode.next = afterNode.next;
        if (afterNode.next != null) {
            afterNode.next.prev = newNode;
        }
        newNode.prev = afterNode;
        afterNode.next = newNode;
        if (afterNode == tail) {
            tail = newNode;
        }
        size++;
    }

    public void insertBefore(T beforeData, T data) {
        Node<T> beforeNode = findByData(beforeData);
        insertBefore(beforeNode, data);
    }

    public void insertBefore(Node<T> beforeNode, T data) {
        if (beforeNode == null) {
            throw new IllegalArgumentException("beforeNode must not be null!");
        }
        if (!existNode(beforeNode)) {
            throw new IllegalArgumentException("beforeNode does not exist!");
        }
        Node<T> newNode = new Node<>(data);
        newNode.next = beforeNode;
        if (beforeNode.prev != null) {
            beforeNode.prev.next = newNode;
        }
        newNode.prev = beforeNode.prev;
        beforeNode.prev = newNode;
        // 头节点
        if (beforeNode == head) {
            head = newNode;
        }
        size++;
    }

    /**
     * 删除指定节点
     *
     * @param delNode delNode
     * @author penggs
     * @since 2020/8/8
     */
    public void deleteByNode(Node<T> delNode) {
        if (delNode == null || head == null) {
            return;
        }
        if (!existNode(delNode)) {
            throw new IllegalArgumentException("delNode does not exist!");
        }
        if (delNode == head) {
            head = delNode.next;
        } else {
            delNode.prev.next = delNode.next;
        }
        if (delNode == tail) {
            tail = delNode.prev;
        } else {
            delNode.next.prev = delNode.prev;
        }
        head.prev = null;
        tail.next = null;
        delNode.prev = null;
        delNode.next = null;
        size--;
    }

    public void deleteByData(T data) {
        Node<T> delNode = findByData(data);
        if (delNode == null) {
            throw new IllegalArgumentException("data not found!");
        }
        deleteByNode(delNode);
    }

    public void deleteHead() {
        if (head == null) {
            return;
        } else if (head.next == null){
            head = null;
            tail = null;
        } else {
            Node<T> p = head.next;
            head.next = null;
            head = p;
            p.prev = null;
        }
        size--;
    }

    public void deleteTail() {
        if (tail == null) {
            return;
        } else if (tail.prev == null) {
            tail = null;
            head = null;
        } else {
            Node<T> p = tail.prev;
            tail.prev = null;
            tail = p;
            tail.next = null;
        }
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
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
     *
     * @author penggs
     * @since 2020/8/8
     */
    static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public static void main(String[] args) {
        ApDoubleLinkedList<String> linkedList = new ApDoubleLinkedList<>();
        linkedList.insertToHead("One");
        linkedList.insertToTail("Two");
        linkedList.insertBefore("One", "BeforeOne");
        linkedList.insertAfter("Two", "AfterTwo");
        ApDoubleLinkedList.Node<String> node = linkedList.findByData("One");
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
        linkedList.deleteHead();
        System.out.println(linkedList.toString());
        System.out.println(linkedList.getSize());
        System.out.println(linkedList.findByIndex(20));
    }
}