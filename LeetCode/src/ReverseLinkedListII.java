/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 11:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // add a dummy node prior to head
        ListNode start = new ListNode(0);
        start.next = head;
        head = start;

        // find start which is the previous node of position m
        for (int i = 0; i < m - 1; i++)
            start = start.next;

        // find end which is the node of position n
        ListNode end = start;
        for (int i = 0; i <= n - m; i++)
            end = end.next;

        // insert "start.next" after "end" until start.next == end
        while (start.next != end) {
            ListNode node = start.next;
            start.next = node.next;
            node.next = end.next;
            end.next = node;
        }

        return head.next;
    }

    /**
     * a better solution which gets it done in one-pass. The previous solution tries to
     * insert current node after "end" node. This solution inserts current node after "start"
     * while travelling through the list until nth node.
     */
    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        head = start;

        ListNode cur = head;
        for (int i = 1; i <= n; i++) {
            if (i < m)
                start = start.next;
            else if (i == m) {
                cur = start.next;
            } else { // i > m
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = start.next;
                start.next = next;
            }
        }

        return head.next;
    }

    /**
     * The same solution as reverseBetween2.
     */
    public ListNode reverseBetween3(ListNode head, int m, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        head = start;

        for (int i = 1; i < m; i++)
            start = start.next;

        ListNode cur = start.next;
        for (int i = 1; i <= n - m; i++) {
            ListNode node = cur.next;
            cur.next = node.next;
            node.next = start.next;
            start.next = node;
        }

        return head.next;
    }
}
