/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/1/13
 * Time: 12:45 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;
import java.util.Stack;

public class BargainingTable22B {
    static class Element {
        int index;
        int value;

        Element(int i, int v) {
            this.index = i;
            this.value = v;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int[] bar = new int[m];
        int max = 0;
        while (n-- > 0) {
            String s = in.next();
            for (int i = 0; i < m; i++) {
                if (s.charAt(i) == '0')
                    bar[i]++;
                else
                    bar[i] = 0;
            }

            max = Math.max(max, calBarMax(bar));
        }
        System.out.println(max);
    }

    static int calBarMax(int[] bar) {
        int max = 0;
        Stack<Element> S = new Stack<Element>();
        S.push(new Element(0, bar[0]));
        for (int i = 1; i < bar.length; i++) {
            int x = bar[i];
            if (x > S.peek().value) {
                S.push(new Element(i, x));
            } else {
                Element pre = null;
                do {
                    pre = S.pop();
                    if (pre.value > 0) {
                        int p = pre.value * 2 + (i - pre.index) * 2;
                        max = Math.max(max, p);
                    }
                } while (!S.empty() && S.peek().value >= x);
                pre.value = x;
                S.push(pre);
            }
        }

        while (!S.empty()) {
            Element e = S.pop();
            if (e.value > 0) {
                int p = (bar.length - e.index) * 2 + e.value * 2;
                max = Math.max(max, p);
            }
        }

        return max;
    }
}
