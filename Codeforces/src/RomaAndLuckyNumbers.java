/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/8/13
 * Time: 12:47 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class RomaAndLuckyNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int k = in.nextInt();

        int[] a = new int[n];
        for(int i = 0; i < n; i++)
            a[i] = in.nextInt();

        int ret = 0;
        for(int i : a) {
            int x = i;
            int c = 0;
            while(x > 0) {
                int rem = x%10;
                if(rem == 4 || rem == 7) {
                    c++;
                    if(c > k)
                        break;
                }
                x /= 10;
            }
            if(c <= k)
                ret++;
        }

        System.out.println(ret);
    }
}
