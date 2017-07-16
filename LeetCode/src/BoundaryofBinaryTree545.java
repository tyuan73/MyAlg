/**
 * Created by yuantian on 7/14/17.
 */

import java.util.*;

/**
 * Left boundary is defined as the path from root to the left-most node.
 * Right boundary is defined as the path from root to the right-most node.
 * If the root doesn't have left subtree or right subtree, then the root
 * itself is left boundary or right boundary. Note this definition only
 * applies to the input binary tree, and not applies to any subtrees.
 * <p>
 * So, for tree: [1,2,null,3,4,null,null,5,6]
 * The output should be [1,2,3,5,6], not [1,2,3,5,6,4]
 */
public class BoundaryofBinaryTree545 {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        ans.add(root.val);
        if (root.left != null) {
            leftPath(root.left, ans);
            leaves(root.left, ans);
        }
        if (root.right != null) {
            leaves(root.right, ans);
            rightPath(root.right, ans);
        }
        return ans;
    }

    private void leaves(TreeNode node, List<Integer> path) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            path.add(node.val);
            return;
        }
        leaves(node.left, path);
        leaves(node.right, path);
    }

    private void leftPath(TreeNode node, List<Integer> path) {
        if (node.left == null && node.right == null) return;
        path.add(node.val);
        if (node.left != null)
            leftPath(node.left, path);
        else
            leftPath(node.right, path);
    }

    private void rightPath(TreeNode node, List<Integer> path) {
        if (node.left == null && node.right == null) return;
        if (node.right != null)
            rightPath(node.right, path);
        else
            rightPath(node.left, path);
        path.add(node.val);
    }
}

/**
 * Improved a little bit in coding.
 */
class Solution545_improved {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        ans.add(root.val);
        if (root.left != null) {
            leftPath(root.left, ans);
            leaves(root.left, ans);
        }
        if (root.right != null) {
            leaves(root.right, ans);
            rightPath(root.right, ans);
        }
        return ans;
    }

    private void leaves(TreeNode node, List<Integer> path) {
        if (node.left == null && node.right == null) {
            path.add(node.val);
            return;
        }
        if (node.left != null) leaves(node.left, path);
        if (node.right != null) leaves(node.right, path);
    }

    private void leftPath(TreeNode node, List<Integer> path) {
        if (node.left == null && node.right == null) return;
        path.add(node.val);
        leftPath(node.left != null ? node.left : node.right, path);
    }

    private void rightPath(TreeNode node, List<Integer> path) {
        if (node.left == null && node.right == null) return;
        rightPath(node.right != null ? node.right : node.left, path);
        path.add(node.val);
    }
}
