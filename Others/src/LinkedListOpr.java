/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/17/13
 * Time: 11:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedListOpr {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) {this.val = v; this.next = null;}
    }

    static public void main(String[] args) {
        ListNode root = new ListNode(0);
        ListNode temp = root;
        for(int i = 0; i < 5; i++)
        {
            ListNode x = new ListNode(i);
             temp.next = x;
            temp = x;
        }

        printList(root);
        root = reverseRecursive(root);
        printList(root);
        root = reverse(root);
        printList(root);

        printList(root);
        root = delete(root, 0);
        printList(root);
    }

    static ListNode reverse(ListNode root) {
        ListNode n1 = null;
        while(root != null) {
            ListNode n2 = root.next;
            root.next = n1;
            n1 = root;
            root = n2;
        }
         return n1;
    }

    static ListNode reverseRecursive(ListNode root) {
        if(root == null)
            return null;
        if(root.next == null)
           return root;
        ListNode newHead = reverseRecursive(root.next);
        root.next.next = root;
        root.next = null;
        return newHead;
    }

    static ListNode delete(ListNode root, int target) {
        if(root == null)
            return null;
        if(root.val == target)
            return delete(root.next, target);
        root.next = delete(root.next, target);
        return root;
    }

    static void printList(ListNode root) {
        if(root == null)
            System.out.println("The list is empty!");

        while(root!=null){
            System.out.print(root.val + "->");
            root = root.next;
        }
        System.out.println();
    }

}
