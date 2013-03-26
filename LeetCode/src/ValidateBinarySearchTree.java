/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/25/13
 * Time: 5:15 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean isValidBST(TreeNode root, int min, int max) {
        if(root == null)
            return true;
        if(root.val < min || root.val > max)
            return false;
        return isValidBST(root.left, min, root.val-1) &&
                isValidBST(root.right, root.val+1, max);
    }
}
