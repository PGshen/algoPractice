package world.playtogether.hot;

/**
 * <project> algoPractice
 *
 * <p> 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 *
 * @author penggs
 * @since 2020-11-08
 */
public class ApAddTwoNumbers {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 记录头节点
        ListNode head = new ListNode(0);
        ListNode cur = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 补零
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        // 最终还有进位，再加一个节点
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return head.next;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }

        @Override
        public String toString() {
            ListNode p = this;
            StringBuilder sb = new StringBuilder();
            while (p != null) {
                sb.append(p.val);
                p = p.next;
                if (p != null) sb.append(" -> ");
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        System.out.println(addTwoNumbers(l1, l2));
    }
}