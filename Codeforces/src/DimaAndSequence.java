/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/8/13
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class DimaAndSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];
        for(int i = 0; i < n; i++)
            a[i] = in.nextInt();

        long[] count = new long[40];
        for(int i : a) {
            int x = i;
            int c = 1;
            while(x > 1) {
                if((x%2) == 1) {
                    c++;
                    x--;
                }
                x >>= 1;
            }
            count[c]++;
        }

        long ret = 0;
        for(long i : count) {
            if(i > 1)
                ret += i*(i-1)/2;
        }
        System.out.println(ret);
    }
}
