/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 2/26/17
 * Time: 12:20 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class SortList148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head, p = null;
        while (fast != null && fast.next != null) {
            slow = (p = slow).next;
            fast = fast.next.next;
        }
        p.next = null;

        ListNode first = sortList(head);
        ListNode second = sortList(slow);

        return merge(first, second);
    }

    ListNode merge(ListNode first, ListNode second) {
        ListNode head = new ListNode(0);
        ListNode runner = head;
        while (first != null && second != null) {
            if (first.val <= second.val) {
                runner.next = first;   // this line and the following line can be replaced with one line: first = (runner.next = first).next;
                first = first.next;
            } else {
                runner.next = second;  // this line and the following line can be replaced with one line: first = (runner.next = second).next;
                second = second.next;
            }
            runner = runner.next;
        }
        if (first == null) runner.next = second;
        if (second == null) runner.next = first;
        return head.next;
    }
}
