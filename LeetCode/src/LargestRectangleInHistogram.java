/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/5/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Stack;

public class LargestRectangleInHistogram {
    class Element {
        int index;
        int value;

        Element(int i, int v) {
            this.index = i;
            this.value = v;
        }
    }

    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;

        Stack<Element> S = new Stack<Element>();
        S.push(new Element(0, height[0]));
        int max = 0;
        for (int i = 1; i < height.length; i++) {
            int h = height[i];
            if (h > S.peek().value)
                S.push(new Element(i, h));
            else {
                Element e = null;
                do {
                    e = S.pop();
                    int area = (i - e.index) * e.value;
                    max = Math.max(max, area);
                } while (!S.empty() && S.peek().value >= h);
                e.value = h;
                S.push(e);
            }
        }

        while (!S.empty()) {
            Element e = S.pop();
            int area = (height.length - e.index) * e.value;
            max = Math.max(max, area);
        }

        return max;
    }

    /**
     * The same as above, but implement stack with a two-dimension array
     */
    public int largestRectangleArea1(int[] height) {
        int n = height.length;
        int max = 0;
        int[][] stack = new int[n + 1][2];
        int index = 0;
        stack[0][0] = Integer.MIN_VALUE;
        stack[0][1] = -1;
        for (int i = 0; i < n; i++) {
            if (height[i] >= stack[index][0]) {
                index++;
                stack[index][0] = height[i];
                stack[index][1] = i;
            } else {
                do {
                    int area = (i - stack[index][1]) * stack[index][0];
                    max = Math.max(max, area);
                    index--;
                } while (stack[index][0] > height[i]);
                stack[++index][0] = height[i];
            }
        }

        while (index > 0) {
            int area = (n - stack[index][1]) * stack[index][0];
            max = Math.max(max, area);
            index--;
        }
        return max;
    }
}
