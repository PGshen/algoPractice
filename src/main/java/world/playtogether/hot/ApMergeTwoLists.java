package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 合并有序链表
 *
 * @author penggs
 * @since 2020-11-15
 */
public class ApMergeTwoLists {
    /**
     * 合并，每次判断链表头大小
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode p = head;
        // 两个链表都不为空
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                p.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                p.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            p = p.next;
        }
        // 两个链表其中一个为空
        while(l1 != null) {
            p.next = new ListNode(l1.val);
            l1 = l1.next;
            p = p.next;
        }
        while(l2 != null) {
            p.next = new ListNode(l2.val);
            l2 = l2.next;
            p = p.next;
        }
        return head.next;
    }

    /**
     * 递归合并两个链表
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1,l2.next);
            return l2;
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode p = this;
            do {
                sb.append(p.val).append(",");
                p = p.next;
            } while (p != null);
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(mergeTwoLists1(l1, l2));
        ListNode l3 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l4 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(mergeTwoLists2(l3, l4));
    }
}