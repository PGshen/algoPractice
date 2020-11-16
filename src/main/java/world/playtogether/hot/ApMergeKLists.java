package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 合并k个有序链表
 * 使用分治法
 *
 * @author penggs
 * @since 2020-11-16
 */
public class ApMergeKLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    /**
     * 分治
     * @param lists 列表
     * @param left 左
     * @param right 右
     * @return
     */
    private static ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    // 递归合并两个链表
    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
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
        ListNode l2 = new ListNode(2, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(1, new ListNode(5, new ListNode(6)));
        ListNode l4 = new ListNode(3, new ListNode(3, new ListNode(8)));
        ListNode[] listNodes = new ListNode[]{l1, l2, l3, l4};
        System.out.println(mergeKLists(listNodes));
    }
}