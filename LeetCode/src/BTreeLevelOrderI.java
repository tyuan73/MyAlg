/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.ArrayList;

public class BTreeLevelOrderI {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        if (root == null)
            return ret;

        ArrayList<TreeNode> level = new ArrayList<TreeNode>();
        level.add(root);
        while (level.size() > 0) {
            ArrayList<Integer> x = new ArrayList<Integer>();
            ArrayList<TreeNode> l1 = new ArrayList<TreeNode>();
            for (TreeNode tn : level) {
                x.add(tn.val);
                if (tn.left != null)
                    l1.add(tn.left);
                if (tn.right != null)
                    l1.add(tn.right);
            }
            ret.add(x);

            level = l1;
        }

        return ret;
    }
}