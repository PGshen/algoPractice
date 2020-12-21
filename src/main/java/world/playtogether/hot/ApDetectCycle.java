package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 环的起点
 *
 * @author penggs
 * @since 2020-12-21
 */
public class ApDetectCycle {


    /**
     * 检测环的起点
     * @param head 头节点
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
        //快慢两个指针
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            //慢指针每次走一步
            slow = slow.next;
            //快指针每次走两步
            fast = fast.next.next;
            //如果相遇，说明有环
            if (slow == fast) {
                // 慢指针重新从头开始
                slow = head;
                do {
                    // 快慢指针同步走，相遇的点即为环的起点
                    if(slow == fast) {
                        return slow;
                    }
                    slow = slow.next;
                    fast = fast.next;
                } while(slow != null);
            }
        }
        //否则就是没环
        return null;
    }

    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
}