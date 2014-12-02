/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/13/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */

public class PopulatingNextRightPointersII {
    public void connect(TreeLinkNode root) {
        while (root != null) {
            TreeLinkNode p = root;
            while (p != null) {
                if (p.left != null) {
                    if (p.right != null)
                        p.left.next = p.right;
                    else
                        p.left.next = getNext(p.next);
                }
                if (p.right != null) {
                    p.right.next = getNext(p.next);
                }
                p = p.next;
            }
            root = getNext(root);
        }
    }

    private TreeLinkNode getNext(TreeLinkNode p) {
        while (p != null) {
            if (p.left != null)
                return p.left;
            if (p.right != null)
                return p.right;
            p = p.next;
        }
        return null;
    }
}
