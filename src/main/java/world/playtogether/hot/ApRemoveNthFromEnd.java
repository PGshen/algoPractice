package world.playtogether.hot;

/**
 <project> algoPractice
 *
 <p> 删除链表的倒数第n个节点
 *
 @author penggs
 @since 2020-11-15
 */
public class ApRemoveNthFromEnd {
    /**
     * 快慢指针
     * @param head 头节点
     * @param n n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode quick = head;
        // 前置指针
        ListNode pre = head;
        boolean delHeadFlag = true;
        // 快指针先走n
        for(int i = 0; i < n; i++) {
            quick = quick.next;
        }
        // 快慢指针同时走，直到快指针到达终点
        while(quick != null) {
            // 进入到这个循环说明删除的不是头节点
            delHeadFlag = false;
            pre = slow;
            slow = slow.next;
            quick = quick.next;
        }
        // 被删除的节点是头指针，头指针需向后移动
        if(delHeadFlag) head = pre.next;
        if(pre.next != null) {
            pre.next = pre.next.next;
        } else {
            // 被删除的是尾节点
            pre.next = null;
        }
        return head;
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
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(removeNthFromEnd(head, 2));
    }
}