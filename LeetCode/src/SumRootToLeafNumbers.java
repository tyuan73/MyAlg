/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/21/13
 * Time: 4:16 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

public class SumRootToLeafNumbers {
    int total = 0;

    public int sumNumbers(TreeNode root) {
        total = 0;
        sumup(root, 0);
        return total;
    }

    void sumup(TreeNode root, int pre) {
        if (root == null)
            return;
        int num = pre * 10 + root.val;
        if (root.left == null && root.right == null)
            total += num;

        sumup(root.left, num);
        sumup(root.right, num);
    }
}
