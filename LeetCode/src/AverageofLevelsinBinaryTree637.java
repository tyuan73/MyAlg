/**
 * Created by miaomiao on 7/8/17.
 */

import java.util.*;

public class AverageofLevelsinBinaryTree637 {
    /**
     * DFS. not the best.
     */
    List<Double> ans = null;
    List<Integer> count = null;

    public List<Double> averageOfLevels(TreeNode root) {
        ans = new ArrayList<>();
        count = new ArrayList<>();
        dfs(root, 0);
        for (int i = 0; i < ans.size(); i++)
            ans.set(i, ans.get(i) / count.get(i));
        return ans;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null) return;
        if (ans.size() <= level) {
            ans.add((double) node.val);
            count.add(1);
        } else {
            ans.set(level, ans.get(level) + (double) node.val);
            count.set(level, count.get(level) + 1);
        }
        dfs(node.left, level + 1);
        dfs(node.right, level + 1);
    }

    /**
     * BFS is a much better solution.
     */
    public List<Double> averageOfLevels1(TreeNode root) {
        List<Double> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            double sum = 0.0;
            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            ans.add(sum / n);
        }
        return ans;
    }
}
