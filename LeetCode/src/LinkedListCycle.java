/**
 * Created by yuantian on 9/4/14.
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;

        ListNode slow = head, fast = head;
        do {
            slow = slow.next;
            fast = fast.next;
            if (fast == null || fast.next == null)
                return false;
            else
                fast = fast.next;
        } while(slow != fast);
        return true;
    }
}
