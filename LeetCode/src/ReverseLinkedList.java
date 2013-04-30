/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 4/3/13
 * Time: 10:17 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class ReverseLinkedList {
    public ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * Recursive version.
     */
    public ListNode reverse2(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode newHead = reverseHelper(head);
        head.next = null;
        return newHead;
    }

    ListNode reverseHelper(ListNode head) {
        if(head.next == null)
            return head;
        ListNode next = head.next;
        ListNode newHead = reverse(next);
        next.next = head;
        return newHead;
    }
}
