/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/16/13
 * Time: 12:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null)
            return null;

        int len = 1;
        ListNode p = head;
        while (p.next != null) {
            len++;
            p = p.next;
        }

        n = n % len;
        /***** the following check is not necessary ******/
        //if(n == 0)
        //    return head;

        p.next = head;
        for (int i = 0; i < len - n; i++) {
            p = p.next;
        }

        head = p.next;
        p.next = null;

        return head;
    }
}
