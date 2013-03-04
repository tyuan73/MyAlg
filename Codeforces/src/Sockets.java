/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 1:37 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class Sockets {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();

        int[] a = new int[n];
        for(int i = 0; i < n; i++)
            a[i] = in.nextInt();

        Arrays.sort(a);
        int total = k;
        while(total < m && n > 0) {
            total += a[--n] -  1;
        }
        if(total >= m) {
            System.out.println(a.length - n);
        } else
            System.out.println(-1);
    }
}
