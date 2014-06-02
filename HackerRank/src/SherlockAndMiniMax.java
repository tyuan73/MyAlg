/**
 * Created by yuantian on 6/2/14.
 */
/*
Watson gives Sherlock an array A1,A2…AN.
He asks him to find a number M between P and Q(both inclusive), such that, min {|Ai-M|, 1 ≤ i ≤ N} is maximised. If there are multiple solutions, print the smallest one.

Input Format
The first line contains N. The next line contains space separated N integers, and denote the array A. The third line contains two space separated integers denoting P and Q.

Output Format
In one line, print the required answer.

Constraints
1 ≤ N ≤ 102
1 ≤ Ai ≤ 109
1 ≤ P ≤ Q ≤ 109

Sample Input

3
5 8 14
4 9
Sample Output

4
Explanation
For M = 4,6,7, or 9, the result is 1. Since we have to output the smallest of the multiple solutions, we print 4.
 */

import java.util.*;

public class SherlockAndMiniMax {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        int p = in.nextInt();
        int q = in.nextInt();

        Arrays.sort(a);
        int min = Integer.MIN_VALUE;
        int out = p;
        for(int i = 1; i < n; i++) {
            if(a[i-1] < p && a[i] > p) {
                int diff = Math.min(p - a[i-1], a[i] - p);
                if (diff >= min) {
                    min = diff;
                    out = p;
                }
            }
            int mid = (a[i]+a[i-1])/2;
            if (mid > p && mid <= q) {
                int diff = mid - a[i - 1];
                if (diff > min) {
                    min = diff;
                    out = mid;
                }
            }
            if(a[i-1] < q && a[i] > q) {
                int diff = Math.min(q - a[i-1], a[i] - q);
                if (diff > min) {
                    min = diff;
                    out = q;
                }
            }
        }

        if (a[0] - p >=  min) {
            min = a[0]-p;
            out = p;
        }
        if (q - a[n-1] > min) {
            out = q;
        }
        System.out.println(out);
    }
}
