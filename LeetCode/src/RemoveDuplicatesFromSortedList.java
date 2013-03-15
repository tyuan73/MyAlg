/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 1:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null)
            return null;

        head.next = delete(head.next, head.val);

        return head;
    }

    ListNode delete(ListNode head, int t) {
        if(head == null)
            return null;
        if(head.val == t)
            return delete(head.next, t);
        head.next = delete(head.next, head.val);
        return head;
    }
}
