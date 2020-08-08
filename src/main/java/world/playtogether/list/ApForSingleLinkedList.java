package world.playtogether.list;

/**
 * <project> algoPractice
 *
 * <p> 链表常用操作
 * 1. 单链表反转
 * 2. 链表中环的检测
 * 3. 两个有序的链表合并
 * 4. 删除链表倒数第 n 个结点
 * 5. 求链表的中间结点
 *
 * @author penggs
 * @since 2020-08-08 10:39
 */
public class ApForSingleLinkedList<T extends Comparable<T>> {

    /**
     * 单链表反转
     * 思路：遍历链表，记录前置节点，修改节点指向前置节点
     *
     * @param head 链表要反转的头节点
     * @return world.playtogether.list.Node<T> 反转后的头节点
     * @author penggs
     * @since 2020/8/8 
     */
    public Node<T> reverse(Node<T> head) {
        // curr 当前节点，pre前置节点
        Node<T> curr = head, pre = null;
        while (curr != null) {
            Node<T> next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }
    
    /** 
     * 检查链表是否存在环
     * 思路：快指针一次走两步，慢指针一次走一步，如果存在环，快指针一定会再次追上慢指针，
     * 
     * @param node 节点 
     * @return boolean 是否存在环 
     * @author penggs 
     * @since 2020/8/8 
     */
    public boolean checkCircle(Node<T> node) {
        if (node == null) {
            return false;
        }
        Node<T> fast = node.next;
        Node<T> slow = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
    
    /** 
     * 合并有序链表
     * 思路：每次取两个链表中的较小（较大）头节点
     * 
     * @param list1 链表1,头节点
	 * @param list2 链表2,头节点
     * @return world.playtogether.list.Node<T> 
     * @author penggs 
     * @since 2020/8/8 
     */
    public Node<T> mergeSortedList(Node<T> list1, Node<T> list2) {
        // 设置一个哨兵节点，保存链表的头
        Node<T> solider = new Node<>();
        // 游走的指针
        Node<T> p = solider;
        while (list1 != null && list2 != null) {
            // 选取两个链表中头节点较小的，接上p，然后移动指针
            if (list1.data.compareTo(list2.data) <= 0) {
                p.next = list1;
                list1 = list1.next;
            } else {
                p.next = list2;
                list2 = list2.next;
            }
            p = p.next;
        }
        if (list1 != null) {
            p.next = list1;
        }
        if (list2 != null) {
            p.next = list2;
        }
        return solider.next;
    }

    /**
     * 删除链表的倒数第K个节点
     * 思路：快指针先走k步，之后慢指针和快指针同步向后走，当快指针到达尾节点时，慢指针所在的位置即为倒数第k个节点，删除它
     *
     * @param list 链表
	 * @param k k
     * @return world.playtogether.list.ApForSingleLinkedList<T>.Node<T>
     * @author penggs
     * @since 2020/8/8
     */
    public Node<T> deleteLastKth(Node<T> list, int k) {
        if (list == null) {
            return list;
        }
        Node<T> fast = list;
        Node<T> slow = list;
        Node<T> prev = null;    // 慢指针的前置指针
        int i = 0;
        // 快指针先走k步
        while (fast != null && i < k) {
            fast = fast.next;
            i++;
        }
        // 没走到k步
        if (i != k) {
            throw new IllegalArgumentException("k is greater than the length of list");
        }
        // 同步走
        while (fast != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        // 前置指针为空，说明在第一个节点
        if (prev == null) {
            list = list.next;
        } else {
            prev.next = prev.next.next;
        }
        return list;
    }

    /**
     * 获取链表的中间节点
     * 思路：快指针一次走两步，慢指针一次走一步，当快指针抵达终点时，慢指针的位置即为链表终点
     *
     * @param list 链表
     * @return world.playtogether.list.ApForSingleLinkedList<T>.Node<T>
     * @author penggs
     * @since 2020/8/8
     */
    public Node<T> getMiddleNode(Node<T> list) {
        if (list == null) {
            return null;
        }
        Node<T> fast = list;
        Node<T> slow = list;
        // 当节点数为偶数时，中间节点实际为 中间两个节点的第二个，可根据实际情况修改边界条件
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public String printList(Node<T> node) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (node != null) {
            sb.append(node.data.toString());
            if (node.next != null) {
                sb.append(",");
            }
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 节点
     */
    public static class Node<T extends Comparable<T>> {
        public T data;
        public Node<T> next;

        public Node() {
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public static void main(String[] args) {
        Node<String> node1 = new Node<>("One", null);
        Node<String> node2 = new Node<>("Two", node1);
        Node<String> node3 = new Node<>("Three", node2);
        Node<String> node4 = new Node<>("Four", node3);
        ApForSingleLinkedList<String> apForSingleLinkedList = new ApForSingleLinkedList<>();
        Node<String> node = apForSingleLinkedList.reverse(node4);
        System.out.println(apForSingleLinkedList.printList(node));
        System.out.println(apForSingleLinkedList.checkCircle(node));
        System.out.println(apForSingleLinkedList.printList(apForSingleLinkedList.getMiddleNode(node)));
        System.out.println(apForSingleLinkedList.printList(apForSingleLinkedList.deleteLastKth(node, 2)));

        Node<String> node5 = new Node<>("Five", null);
        Node<String> node6 = new Node<>("Six", node5);
        System.out.println(apForSingleLinkedList.printList(apForSingleLinkedList.mergeSortedList(node, node6)));
    }
}