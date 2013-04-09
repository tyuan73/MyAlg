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
}
