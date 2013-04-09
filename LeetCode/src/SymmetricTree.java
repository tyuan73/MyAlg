/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/21/13
 * Time: 11:36 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class SymmetricTree {
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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }
}
