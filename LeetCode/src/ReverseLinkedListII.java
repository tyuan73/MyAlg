/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 11:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode p1 = new ListNode(-1);
        p1.next = head;
        head = p1;
        ListNode p2 = p1;
        for (int i = 0; i < n; i++) {
            if (i < m - 1)
                p1 = p1.next;
            p2 = p2.next;
        }

        for (int i = 0; i < n - m; i++) {
            ListNode p3 = p1.next;
            p1.next = p3.next;
            p3.next = p2.next;
            p2.next = p3;
        }

        return head.next;
    }
}
