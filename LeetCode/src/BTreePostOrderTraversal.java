/**
 * Created by yuantian on 8/22/14.
 */

import java.util.*;

public class BTreePostOrderTraversal {

    /**
     * Iterative solution
     * http://leetcode.com/2010/10/binary-tree-post-order-traversal.html
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        if (root == null)
            return ans;

        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        TreeNode pre = null;

        while (!s.empty()) {
            TreeNode cur = s.peek();
            if (pre == null || pre.left == cur || pre.right == cur) {
                if (cur.left != null) {
                    s.push(cur.left);
                } else if (cur.right != null) {
                    s.push(cur.right);
                } else {
                    s.pop();
                    ans.add(cur.val);
                }
            } else if (cur.left == pre) {
                if (cur.right != null) {
                    s.push(cur.right);
                } else {
                    s.pop();
                    ans.add(cur.val);
                }
            } else if (cur.right == pre) {
                s.pop();
                ans.add(cur.val);
            }
            pre = cur;
        }

        return ans;
    }


    /** easy recursive solution **/
    /*
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        internal(root, ans);
        return ans;
    }

    private void internal(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        internal(root.left, list);
        internal(root.right, list);
        list.add(root.val);
    }
     */
}
