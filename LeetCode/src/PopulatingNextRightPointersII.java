/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/13/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */

public class PopulatingNextRightPointersII {
    public void connect(TreeLinkNode root) {
        TreeLinkNode p;
        while ((p = getNext(root)) != null) {
            TreeLinkNode q = root;
            while (q != null) {
                if (q.left != null && q.right != null) {
                    q.left.next = q.right;
                    q.right.next = getNext(q.next);
                } else if (q.left != null || q.right != null) {
                    TreeLinkNode child = q.left != null ? q.left : q.right;
                    child.next = getNext(q.next);
                }
                q = q.next;
            }

            root = p;
        }
    }

    TreeLinkNode getNext(TreeLinkNode root) {
        while (root != null) {
            if (root.left != null)
                return root.left;
            if (root.right != null)
                return root.right;
            root = root.next;
        }
        return null;
    }
}
