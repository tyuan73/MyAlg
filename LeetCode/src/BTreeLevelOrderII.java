/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.*;

public class BTreeLevelOrderII {

    /* the same as BTreeLevelOrderI. just reverse the list before returning it. */
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> level = new ArrayList<ArrayList<TreeNode>>();
        if (root == null)
            return new ArrayList<ArrayList<Integer>>();

        ArrayList<TreeNode> l1 = new ArrayList<TreeNode>();
        l1.add(root);
        while (l1.size() > 0) {
            level.add(l1);
            ArrayList<TreeNode> l2 = new ArrayList<TreeNode>();
            for (TreeNode tn : l1) {
                if (tn.left != null)
                    l2.add(tn.left);
                if (tn.right != null)
                    l2.add(tn.right);
            }
            l1 = l2;
        }

        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        for (int i = level.size() - 1; i >= 0; i--) {
            ArrayList<Integer> x = new ArrayList<Integer>();
            for (TreeNode tn : level.get(i))
                x.add(tn.val);

            ret.add(x);
        }

        return ret;
    }

    /* a better solution. use linked list and insert levels at position 0 */
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();

        if (root == null)
            return ret;

        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);

        while(list.size() > 0) {
            List<TreeNode> nextLevel = new ArrayList<TreeNode>();
            List<Integer> level = new ArrayList<Integer>();
            for(TreeNode node: list) {
                level.add(node.val);
                if (node.left != null)
                    nextLevel.add(node.left);
                if (node.right != null)
                    nextLevel.add(node.right);
            }
            ret.add(0, level);
            list = nextLevel;
        }

        return ret;
    }
}