/**
 * Created by yuantian on 10/15/14.
 */

import java.util.*;

public class MergeKSortedLists {
    public ListNode mergeKLists(List<ListNode> lists) {
        return mergeLists(lists, 0, lists.size() - 1);
    }

    /* Top-down / recursive solution */
    public ListNode mergeLists(List<ListNode> lists, int from, int to) {
        if (from > to)
            return null;
        if (from == to)
            return lists.get(from);
        ListNode l1 = mergeLists(lists, from, (from + to) / 2);
        ListNode l2 = mergeLists(lists, (from + to) / 2 + 1, to);
        return merge(l1, l2);
    }

    /* Bottom-up / iterative solution */
    public ListNode mergeKLists1(List<ListNode> lists) {
        int s = lists.size();
        if (s == 0) return null;
        for (int step = 1; step < s; step *= 2) {
            for (int i = 0; i < s; i += (step << 1)) {
                if (i + step < s)
                    lists.set(i, merge(lists.get(i), lists.get(i + step)));
            }
        }
        return lists.get(0);
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
                p = p.next;
            } else {
                p.next = l2;
                l2 = l2.next;
                p = p.next;
            }
        }
        if (l1 != null)
            p.next = l1;
        if (l2 != null)
            p.next = l2;
        return head.next;
    }
}
