/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 4/3/17
 * Time: 10:23 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class PlusOneLinkedList369 {
    /**
     * Recursive solution
     */
    public ListNode plusOne(ListNode head) {
        int ans = increase(head);
        if (ans == 1) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            head = newHead;
        }
        return head;
    }

    private int increase(ListNode node) {
        if (node == null) return 1;
        int res = increase(node.next);
        node.val += res;
        res = node.val / 10;
        node.val %= 10;
        return res;
    }

    /**
     * iterative solution
     */
    public ListNode plusOne1(ListNode head) {
        ListNode i = new ListNode(0);
        ListNode j = i;
        i.next = head;
        head = i;

        // find the last digit that is not 9
        while (j != null) {
            if (j.val != 9)
                i = j;
            j = j.next;
        }
        // i = the last non-9 digit. all digits ater i are 9s

        i.val++;
        while (i.next != null) {
            i = i.next;
            i.val = 0;
        }
        return head.val == 0 ? head.next : head;
    }

    public static void main(String[] args) {
        PlusOneLinkedList369 test = new PlusOneLinkedList369();
        System.out.println("Hello!");
    }
}
