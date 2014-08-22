/**
 * Created by yuantian on 8/22/14.
 */

import java.util.*;

public class BTreePreOrderTraversal {

    /**
     * Iterative solution
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();

        if (root == null)
            return ans;

        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while(!s.empty()) {
            TreeNode cur = s.pop();
            ans.add(cur.val);
            if (cur.right != null)
                s.push(cur.right);
            if (cur.left != null)
                s.push(cur.left);
        }

        return ans;
    }

    /**
     * easy recursive solution
     */
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> ans = new ArrayList<Integer>();
        internal(root, ans);
        return ans;
    }

    private void internal(TreeNode root, List<Integer> ans) {
        if (root == null)
            return;
        ans.add(root.val);
        internal(root.left, ans);
        internal(root.right, ans);
    }
}
