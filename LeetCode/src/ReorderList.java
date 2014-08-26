/**
 * Created by yuantian on 8/26/14.
 */
public class ReorderList {
    /*
    the idea is to reverse the second half link list and merge the first
    half link list with the reversed second half link list.
     */
    public void reorderList(ListNode head) {
        if (head == null)
            return;

        // find the end of the first half.
        // for example: 1->2->3->4->5, p1 will point to 3.
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2 != null) {
            p2 = p2.next;
            if (p2 != null)
                p2 = p2.next;
            else
                break;
            p1 = p1.next;
        }

        // break the link list from the middle
        // p2 is the head of the second link list
        p2 = p1.next;
        p1.next = null;

        // reverse the second list
        ListNode pre = null;
        ListNode next = null;
        while (p2 != null) {
            next = p2.next;
            p2.next = pre;
            pre = p2;
            p2 = next;
        }

        // merge the 2 sub lists
        p1 = head;
        p2 = pre;

        // since the length of the second sub list is
        // equal to or shorter than the first sub list,
        // don't need to check if (p1 != null)
        while (p2 != null) {
            ListNode n1 = p1.next;
            ListNode n2 = p2.next;
            p1.next = p2;
            p2.next = n1;
            p1 = n1;
            p2 = n2;
        }
    }
}
