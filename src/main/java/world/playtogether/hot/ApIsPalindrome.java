package world.playtogether.hot;

import java.util.Stack;

/**
 * 是否回文
 */
public class ApIsPalindrome {

    // 判断一个数字是否是回文
    public static boolean isPalindrome(int num) {
        // 负数和10的整数倍可能不符合
        if (num < 0 || (num % 10 == 0 && num != 0)) return false;
        int revertedNum = 0;
        // 后半段反向生成数值
        while (num > revertedNum) {
            revertedNum = revertedNum * 10 + num % 10;
            num /= 10;
        }
        // revertedNum可能更大
        return revertedNum == num || num == revertedNum/10;
    }

    /**
     * 使用棧
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }
        while (head != null) {
            if (stack.pop() != head.val) return false;
            head = head.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        ListNode fast = head, slow = head;
        //通过快慢指针找到中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果fast不为空，说明链表的长度是奇数个
        if (fast != null) {
            slow = slow.next;
        }
        //反转后半部分链表
        slow = reverse(slow);

        fast = head;
        while (slow != null) {
            //然后比较，判断节点值是否相等
            if (fast.val != slow.val)
                return false;
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    //反转链表
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static void main(String[] args) {
        System.out.println(isPalindrome(1221));
        System.out.println(isPalindrome(1000));
        System.out.println(isPalindrome(-22));
        System.out.println(isPalindrome(121));
    }
}
