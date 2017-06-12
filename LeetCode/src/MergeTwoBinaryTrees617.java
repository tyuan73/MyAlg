/**
 * Created by yuantian on 6/12/17.
 */

import java.util.*;

public class MergeTwoBinaryTrees617 {
    /**
     * Recursive.
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    /**
     * Iteravive.
     */
    public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        Stack<TreeNode> n1 = new Stack<>();
        Stack<TreeNode> n2 = new Stack<>();
        n1.push(t1);
        n2.push(t2);
        while (!n1.isEmpty()) {
            TreeNode tt1 = n1.pop();
            TreeNode tt2 = n2.pop();
            tt1.val += tt2.val;
            if (tt1.left == null) {
                tt1.left = tt2.left;
            } else {
                if (tt2.left != null) {
                    n1.push(tt1.left);
                    n2.push(tt2.left);
                }
            }
            if (tt1.right == null) {
                tt1.right = tt2.right;
            } else {
                if (tt2.right != null) {
                    n1.push(tt1.right);
                    n2.push(tt2.right);
                }
            }
        }
        return t1;
    }
}
