/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 10:50 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class ConvertSortedListToBST {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);

    }

    public TreeNode sortedListToBST(ListNode head) {
        int n = 0;
        ListNode p = head;
        while(p != null) {
            n++; p = p.next;
        }
        ListNode[] q = new ListNode[1];
        q[0] = head;
        return helper(q, 0, n-1);
    }

    TreeNode helper(ListNode[] q, int l, int r) {
        if(l > r)
            return null;
        int mid = (l+r)/2;
        TreeNode leftChild = helper(q, l, mid-1);
        TreeNode parent = new TreeNode(q[0].val);
        q[0] = q[0].next;
        TreeNode rightChild = helper(q, mid+1, r);
        parent.left = leftChild;
        parent.right = rightChild;
        return parent;
    }
}
