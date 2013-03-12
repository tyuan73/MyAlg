/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/12/13
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class HowManyMatrices {
    public static void main(String[] args) {
        HowManyMatrices hm = new HowManyMatrices();
        //hm.process(1, 6);
        //hm.process(2, 3);

        //hm.factoral(21);
        ArrayList<Integer[]> table = hm.getTableSize(1000000000);

        for(Integer[] a : table)
             System.out.println(a[0] + " " + a[1]);



    }

    ArrayList<Integer[]> getTableSize(int n) {
        ArrayList<Integer[]> ret = new ArrayList<Integer[]>();
        //Integer[] pair = {1, n};
        //ret.add(pair);

        int sqrt = (int) Math.sqrt(n);
        for(int i = 1; i <= sqrt; i++) {
            if((n % i) == 0) {
                Integer[] a = {i, n/i};
                ret.add(a);
            }
        }
        return ret;
    }

    void factoral(int a) {
        long[] table = new long[a+1];
        table[0] = 1;
        for(int i = 1; i <= a; i++) {
            table[i] = table[i-1]*i;
        }

        for(long x : table)
            System.out.println(x);
    }

    void process(int r, int c) {
        long f1 = 1;
        for(int i = 1; i <= r*c; i++)
            f1 *= i;
        long f2 = 1;
        for(int i = 1; i < c; i++) {
            long x = 1;
            for(int j = 1; j <= i; j++)
                x *= j;
            f2 *= x;
        }
        long f3 = 1;
        for(int i = r; i < c+r; i++) {
            long x = 1;
            for(int j = 1; j <= i; j++)
                x *= j;
            f3 *= x;
        }

        System.out.println("f1 = " + f1 + " f2 = " + f2 + " f3 = " + f3);
        System.out.println(f1*f2/f3);
    }
}
