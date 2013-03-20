/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class ConvertSortedArrayToBST {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

    }

    public TreeNode sortedArrayToBST(int[] num) {
        return convert(num, 0, num.length - 1);
    }

    TreeNode convert(int[] num, int l, int r) {
        if (l > r)
            return null;

        int mid = (l + r) / 2;
        TreeNode n = new TreeNode(num[mid]);
        n.left = convert(num, l, mid - 1);
        n.right = convert(num, mid + 1, r);
        return n;
    }
}
