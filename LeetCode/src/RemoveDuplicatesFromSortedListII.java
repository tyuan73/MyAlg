/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/15/13
 * Time: 1:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        if (head.val == head.next.val)
            return delete(head, head.val);

        head.next = deleteDuplicates(head.next);
        return head;
    }

    ListNode delete(ListNode head, int t) {
        ListNode n = head.next;
        while (n != null && n.val == t) {
            n = n.next;
        }
        if (n == null || n.next == null)
            return n;

        if (n.val == n.next.val)
            return delete(n, n.val);

        n.next = deleteDuplicates(n.next);
        return n;
    }

    /**
     * A better solution using recursive
     */
    Integer last = null;

    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null)
            return null;
        head.next = deleteDuplicates(head.next);

        if (head.next != null && head.next.val == head.val) {
            last = head.val;
            return head.next.next;
        }

        if (last != null && head.val == last) {
            return head.next;
        }
        return head;
    }

    /**
     * A non-recursive solution using 2 pointers
     */
    public ListNode deleteDuplicates2(ListNode head) {
        //use two pointers, slow - track the node before the dup nodes,
        // fast - to find the last node of dups.
        ListNode dummy = new ListNode(0), fast = head, slow = dummy;
        slow.next = fast;
        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val) {
                fast = fast.next;    //while loop to find the last node of the dups.
            }
            if (slow.next != fast) { //duplicates detected.
                slow.next = fast.next; //remove the dups.
                fast = slow.next;     //reposition the fast pointer.
            } else { //no dup, move down both pointer.
                slow = slow.next;
                fast = fast.next;
            }

        }
        return dummy.next;
    }
}
