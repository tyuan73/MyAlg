/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 1:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        if (head.val == head.next.val)
            return delete(head, head.val);

        head.next = deleteDuplicates(head.next);
        return head;
    }

    ListNode delete(ListNode head, int t) {
        ListNode n = head.next;
        while (n != null && n.val == t) {
            n = n.next;
        }
        if (n == null || n.next == null)
            return n;

        if (n.val == n.next.val)
            return delete(n, n.val);

        n.next = deleteDuplicates(n.next);
        return n;
    }
}
