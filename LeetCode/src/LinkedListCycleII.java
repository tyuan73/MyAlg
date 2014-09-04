/**
 * Created by yuantian on 9/4/14.
 */
public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null)
            return null;
        ListNode slow = head, fast = head;
        do {
            slow = slow.next;
            fast = fast.next;
            if (fast == null || fast.next == null)
                return null;
            else
                fast = fast.next;
        } while (slow != fast);

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
