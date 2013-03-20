/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 3:26 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;

public class PathSumII {
    public static void main(String[] args) {
        PathSumII ps = new PathSumII();
        TreeNode root = new TreeNode(1);
        TreeNode n = new TreeNode(2);
        root.left = n;
        ps.pathSum(root, 0);
    }

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        helper(root, sum, new ArrayList<Integer>(), ret);
        return ret;
    }

    void helper(TreeNode root, int sum, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> ret) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                list.add(root.val);
                ret.add((ArrayList<Integer>) list.clone());
                list.remove(list.size() - 1);
            }
            return;
        }

        list.add(root.val);
        helper(root.left, sum - root.val, list, ret);

        helper(root.right, sum - root.val, list, ret);
        list.remove(list.size() - 1);
    }
}
