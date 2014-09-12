/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class PathSum {
    /* recursive version */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;

        if (root.left == null && root.right == null)
            return sum == root.val;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /* iterative version - inorder travel */
    public boolean hasPathSum1(TreeNode root, int sum) {
        Stack<TreeNode> s = new Stack<TreeNode>();
        Stack<Integer> t = new Stack<Integer>();

        pushLeft(root, s, t, sum);

        while (s.size() > 0) {
            TreeNode node = s.pop();
            int target = t.pop();
            if (node.left == null && node.right == null && node.val == target) {
                return true;
            }

            target -= node.val;
            pushLeft(node.right, s, t, target);
        }

        return false;
    }

    private void pushLeft(TreeNode root, Stack<TreeNode> s, Stack<Integer> t, int target) {
        while (root != null) {
            s.push(root);
            t.push(target);
            target -= root.val;
            root = root.left;
        }
    }

    /* another iterative solution - preorder */
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null)
            return false;

        Stack<TreeNode> s = new Stack<TreeNode>();
        Stack<Integer> t = new Stack<Integer>();

        s.push(root);
        t.push(sum);

        while (s.size() > 0) {
            TreeNode node = s.pop();
            int target = t.pop();
            if (node.left == null && node.right == null && node.val == target) {
                return true;
            }

            if (node.right != null) {
                s.push(node.right);
                t.push(target - node.val);
            }
            if (node.left != null) {
                s.push(node.left);
                t.push(target - node.val);
            }
        }

        return false;
    }
}
