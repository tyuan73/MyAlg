/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/21/13
 * Time: 4:16 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.ArrayList;

public class SumRootToLeafNumbers {
    int total = 0;

    public int sumNumbers(TreeNode root) {
        total = 0;
        dfs(root, new ArrayList<TreeNode>());
        return total;
    }

    void dfs(TreeNode root, ArrayList<TreeNode> list) {
        if (root == null)
            return;

        list.add(root);
        if (root.left == null && root.right == null)
            add(list);

        dfs(root.left, list);
        dfs(root.right, list);
        list.remove(list.size() - 1);
    }

    void add(ArrayList<TreeNode> list) {
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            num = num * 10 + list.get(i).val;
        }
        total += num;
    }
}
