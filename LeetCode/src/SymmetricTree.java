/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/21/13
 * Time: 11:36 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;
import java.util.Stack;

public class SymmetricTree {
    /* recursive solution */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if (left == null || right == null)
            return false;

        return left.val == right.val
                && isSymmetric(left.left, right.right)
                && isSymmetric(left.right, right.left);
    }

    /* iterative solution - preorder travel */
    public boolean isSymmetric1(TreeNode root) {
        if (root == null)
            return true;
        Stack<TreeNode> s1 = new Stack<TreeNode>();
        Stack<TreeNode> s2 = new Stack<TreeNode>();
        s1.push(root.left);
        s2.push(root.right);
        while (!s1.empty() && !s2.empty()) {
            TreeNode n1 = s1.pop();
            TreeNode n2 = s2.pop();
            if (n1 == null && n2 == null)
                continue;
            if (n1 == null || n2 == null || n1.val != n2.val)
                return false;
            s1.push(n1.left);
            s1.push(n1.right);
            s2.push(n2.right);
            s2.push(n2.left);
        }

        //return s1.size() == s2.size();
        return true; // it does not matter if the size matches.
    }

    /* exactly the same as above, but with one stack */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null)
            return true;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root.left);
        s.push(root.right);
        while (!s.empty()) {
            TreeNode n1 = s.pop();
            TreeNode n2 = s.pop();
            if (n1 == null && n2 == null)
                continue;
            if (n1 == null || n2 == null || n1.val != n2.val)
                return false;
            s.push(n1.left);
            s.push(n2.right);
            s.push(n1.right);
            s.push(n2.left);
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
