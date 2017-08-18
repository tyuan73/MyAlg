
import java.util.*;

public class TwoSumIV653 {
    HashSet<Integer> set = null;

    public boolean findTarget(TreeNode root, int k) {
        set = new HashSet<>();
        return check(root, k);
    }

    private boolean check(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(root.val)) return true;
        set.add(k - root.val);
        return check(root.left, k) || check(root.right, k);
    }
}
