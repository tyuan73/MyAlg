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
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> level = new ArrayList<ArrayList<TreeNode>>();
        if(root == null)
            return new ArrayList<ArrayList<Integer>>();
        
        ArrayList<TreeNode> l1 = new ArrayList<TreeNode>();
        l1.add(root);
        while(l1.size() > 0) {
            level.add(l1);
            ArrayList<TreeNode> l2 = new ArrayList<TreeNode>();
            for(TreeNode tn : l1) {
                if(tn.left != null)
                    l2.add(tn.left);
                if(tn.right != null)
                    l2.add(tn.right);
            }
            l1 = l2;
        }
        
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        for(int i = level.size() - 1; i >= 0; i--) {
            ArrayList<Integer> x = new ArrayList<Integer>();
            for(TreeNode tn : level.get(i))
                x.add(tn.val);
            
            ret.add(x);
        }
        
        return ret;
    }
}