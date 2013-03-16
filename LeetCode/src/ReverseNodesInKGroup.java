/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 11:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode p1 = new ListNode(-1);
        p1.next = head;
        head = p1;
        ListNode p2 = p1;

        while(true) {
            for(int i = 0; i < k; i++) {
                if(p2.next != null)
                    p2 = p2.next;
                else
                    return head.next;
            }

            ListNode p3 = p1.next;
            for(int i = 0; i < k-1; i++) {
                ListNode p4 = p1.next;
                p1.next = p4.next;
                p4.next = p2.next;
                p2.next = p4;
            }
            p1 = p3;
            p2 = p3;
        }
        //return head.next;          // unreachable return statement.
    }
}
