/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 2:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < n; i++)
            p2 = p2.next;
        if (p2 == null)
            return head.next;

        while (p2.next != null) {
            p2 = p2.next;
            p1 = p1.next;
        }

        p1.next = p1.next.next;

        return head;
    }
}
