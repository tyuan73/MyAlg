/*

*/

import java.util.*;
import java.io.*;

public class SplitLinkedListinParts725 {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] ans = new ListNode[k];
        int len = 0;
        ListNode node = root;
        while (node != null) {
            len++;
            node = node.next;
        }
        int m = len / k, p = len % k;
        node = root;
        for (int i = 0; i < k && node != null; i++) {
            ans[i] = node;
            int count = p-- > 0 ? m + 1 : m;
            while (count-- > 1) {
                node = node.next;
            }
            ListNode next = node.next;
            node.next = null;
            node = next;
        }
        return ans;
    }

}