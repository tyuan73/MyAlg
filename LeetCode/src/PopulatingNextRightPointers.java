/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/13/13
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class PopulatingNextRightPointers {
    public void connect(TreeLinkNode root) {
        if(root == null)
            return;
        while(root.left != null) {
            TreeLinkNode p = root;
            while(p != null) {
                p.left.next = p.right;
                if(p.next != null)
                    p.right.next = p.next.left;
                p = p.next;
            }
            root = root.left;
        }
    }
}
