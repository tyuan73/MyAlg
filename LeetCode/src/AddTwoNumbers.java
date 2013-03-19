/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode ret = new ListNode(0);
        ListNode pre = ret;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int a1 = l1 == null ? 0 : l1.val;
            int a2 = l2 == null ? 0 : l2.val;
            int sum = a1 + a2 + carry;
            carry = sum > 9 ? 1 : 0;
            ListNode n = new ListNode(sum % 10);
            pre.next = n;
            pre = n;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        if (carry > 0)
            pre.next = new ListNode(carry);

        return ret.next;
    }
}