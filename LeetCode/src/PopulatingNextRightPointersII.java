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

    /**
     * A better solution to use a fake header as next line header
     */
    public void connect1(TreeLinkNode root) {
        TreeLinkNode lineHead = new TreeLinkNode(0);
        TreeLinkNode travel = lineHead;
        while (root != null) {
            travel = lineHead;
            lineHead.next = null;
            while (root != null) {
                if (root.left != null) {
                    travel.next = root.left;
                    travel = travel.next;
                }
                if (root.right != null) {
                    travel.next = root.right;
                    travel = travel.next;
                }
                root = root.next;
            }
            root = lineHead.next;
        }
    }
}
