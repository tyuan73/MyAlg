/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/7/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val > l2.val) {
            ListNode x = l1;
            l1 = l2;
            l2 = x;
        }
        ListNode head = l1;

        while (l1.next != null) {
            while (l1.next != null && l1.next.val < l2.val)
                l1 = l1.next;
            if (l1.next != null) {
                ListNode l = l2.next;
                l2.next = l1.next;
                l1.next = l2;
                l2 = l;
                if (l2 == null)
                    return head;
            }
        }
        l1.next = l2;

        return head;
    }
}
