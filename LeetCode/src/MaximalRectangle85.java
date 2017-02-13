/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/7/13
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class MaximalRectangle85 {

    /**
     * Solution 1: use stack.
     */
    class Element {
        int index;
        int value;

        Element(int i, int v) {
            this.index = i;
            this.value = v;
        }
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length;
        int m = matrix[0].length;

        int max = 0;
        int[] height = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1')
                    height[j]++;
                else
                    height[j] = 0;
            }

            max = Math.max(max, getMax(height));
        }

        return max;
    }

    int getMax(int[] height) {
        int n = height.length;
        int max = 0;
        Stack<Element> s = new Stack<Element>();
        s.push(new Element(0, height[0]));
        for (int i = 1; i < n; i++) {
            int h = height[i];
            if (h > s.peek().value)
                s.push(new Element(i, h));
            else {
                Element e = null;
                do {
                    e = s.pop();
                    int area = (i - e.index) * e.value;
                    max = Math.max(max, area);
                } while (!s.empty() && h <= s.peek().value);
                e.value = h;
                s.push(e);
            }
        }

        while (!s.empty()) {
            Element e = s.pop();
            int area = (n - e.index) * e.value;
            max = Math.max(max, area);
        }

        return max;
    }

    /**
     * Solution 2: DP
     * https://discuss.leetcode.com/topic/6650/share-my-dp-solution
     */
    public int maximalRectangleDP(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix[0].length;
        int[] height = new int[m], left = new int[m], right = new int[m];
        Arrays.fill(right, m);
        int max = 0;
        for (char[] row : matrix) {
            // calculate height
            for (int i = 0; i < m; i++) {
                if (row[i] == '0')
                    height[i] = 0;
                else
                    height[i]++;
            }

            // find left most "1" in continuous "1"s
            int cur_left = 0;
            for (int i = 0; i < m; i++) {
                if (row[i] == '1')
                    left[i] = Math.max(cur_left, left[i]);
                else {
                    cur_left = i + 1;
                    left[i] = 0;
                }
            }

            // find the first "0" after continuous "1"s
            int cur_right = m;
            for (int i = m - 1; i >= 0; i--) {
                if (row[i] == '1')
                    right[i] = Math.min(cur_right, right[i]);
                else {
                    cur_right = i;
                    right[i] = m;
                }
            }

            // calculate area: (right - left) * height
            for (int i = 0; i < m; i++)
                max = Math.max(max, (right[i] - left[i]) * height[i]);
        }
        return max;
    }
}
