package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * @author penggs
 * @since 2021-01-04
 */
public class ApIntersectionOfTwoLinkedLists {
    /**
     * 设长-短链表为 C，短-长链表为 D （分别代表长链表在前和短链表在前的拼接链表），则当 C 走到长短链表交接处时，D 走在长链表中，且与长链表头距离为 长度差;
     * 当 ha == hb 时跳出，返回即可
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        while(pA != pB) {
            // A, B 拼接后同步向后走
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
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