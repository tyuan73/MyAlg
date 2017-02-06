/**
 * Created by yuantian on 2/6/17.
 */
public class PartitionList86 {
    public ListNode partition(ListNode head, int x) {
        //if (head == null) return null;
        ListNode p1 = new ListNode(0), q1 = p1, p2 = new ListNode(0), q2 = p2;
        ListNode next = head;
        while (next != null) {
            ListNode temp = next.next;
            if (next.val < x) {
                q1.next = next;
                q1 = next;
                q1.next = null;
            } else {
                q2.next = next;
                q2 = next;
                q2.next = null;
            }

            next = temp;
        }
        q1.next = p2.next;
        return p1.next;
    }
}
