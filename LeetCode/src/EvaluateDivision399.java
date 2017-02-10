/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 2/9/17
 * Time: 10:49 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.*;

public class EvaluateDivision399 {
    class Node {
        int next;
        double val;

        Node(int next, double val) {
            this.next = next;
            this.val = val;
        }
    }

    double multi = 1.0;

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        int idx = 0;
        Map<String, Integer> map = new HashMap<>();
        List<List<Node>> g = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            String s1 = equations[i][0];
            String s2 = equations[i][1];
            if (!map.containsKey(s1)) {
                map.put(s1, idx++);
                g.add(new ArrayList<Node>());
            }
            if (!map.containsKey(s2)) {
                map.put(s2, idx++);
                g.add(new ArrayList<Node>());
            }
            if (!s1.equals(s2)) {
                int n1 = map.get(s1), n2 = map.get(s2);
                Node node = new Node(n2, values[i]);
                g.get(n1).add(node);
                node = new Node(n1, 1 / values[i]);
                g.get(n2).add(node);
            }
        }
        double[] ans = new double[queries.length];
        boolean[] visited = new boolean[idx];
        for (int i = 0; i < ans.length; i++) {
            String s1 = queries[i][0];
            String s2 = queries[i][1];
            if (!map.containsKey(s1) || !map.containsKey(s2)) {
                ans[i] = -1.0;
                continue;
            }
            if (s1.equals(s2)) {
                ans[i] = 1.0;
                continue;
            }
            int n1 = map.get(s1), n2 = map.get(s2);
            Arrays.fill(visited, false);
            multi = 1.0;
            if (dfs(g, visited, n1, n2, 1.0))
                ans[i] = multi;
            else
                ans[i] = -1.0;
        }
        return ans;
    }

    boolean dfs(List<List<Node>> g, boolean[] visited, int cur, int target, double m) {
        visited[cur] = true;
        for (Node node : g.get(cur)) {
            if (!visited[node.next]) {
                if (node.next == target) {
                    multi = m * node.val;
                    return true;
                }
                if (dfs(g, visited, node.next, target, m * node.val))
                    return true;
            }
        }
        return false;
    }
}
