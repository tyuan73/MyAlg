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

public class BTreeZigZagLevelOrder {
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        if(root == null)
            return ret;
        
        ArrayList<TreeNode> level = new ArrayList<TreeNode>();
        level.add(root);
        boolean leftToRight = true;
        while(level.size() > 0) {
            ArrayList<TreeNode> l1 = new ArrayList<TreeNode>();
            for(TreeNode tn : level) {
                if(tn.left != null)
                    l1.add(tn.left);
                if(tn.right != null)
                    l1.add(tn.right);
            }
            
            ArrayList<Integer> x  = new ArrayList<Integer>();
            if(leftToRight) {
                for(TreeNode tn : level)
                    x.add(tn.val);
            } else {
                for(int i = level.size() - 1; i >= 0; i--)
                    x.add(level.get(i).val);
            }
            ret.add(x);
            
            level = l1;
            leftToRight = !leftToRight;
        }
        return ret;
    }
}