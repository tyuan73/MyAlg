/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/16/13
 * Time: 12:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class RotateList61 {
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

    /**
     * Slightly better in coding
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight1(ListNode head, int k) {
        if (head == null) return null;

        ListNode last = head;
        int len;
        for (len = 1; last.next != null; len++)   // count len of the list
            last = last.next;

        last.next = head;                         // link the last to head to make a loop
        len -= k % len;                           // calculate how many nodes need to skip
        for (int i = 0; i < len; i++)             // skip nodes
            last = last.next;

        head = last.next;                         // break loop
        last.next = null;

        return head;
    }
}
