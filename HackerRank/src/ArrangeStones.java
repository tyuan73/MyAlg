/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/12/13
 * Time: 11:29 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;
import java.math.*;

public class ArrangeStones {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long m = in.nextLong();
        long n = in.nextLong();

        BigInteger ret = new BigInteger("1");
        for(long i = n+2; i <= m+n; i++)
            ret = ret.multiply(new BigInteger(""+i));
        ret = ret.multiply(new BigInteger("" + (n - m + 1)));
        //System.out.println(ret);
        for(long i = 1; i <= m; i++)
            ret = ret.divide(new BigInteger(""+i));
        System.out.println(ret);

       /*
        int x = 5;
        int count = 0;
        while(m >= x) {
            count += m/x;
            x *= 5;
        }
        System.out.println(count);
        */
    }
}
