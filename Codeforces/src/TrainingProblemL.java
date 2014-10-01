/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 9/26/13
 * Time: 9:48 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

public class TrainingProblemL {
    static long l, r;
    static int x;
    static long total;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while( (l = in.nextLong()) != 0) {
            r = in.nextLong();
            x = in.nextInt();
            total = 0;

            if (x > 4) {
                System.out.println(0);
                continue;
            }
            if (x == 0) {
                if(l == 1)
                    System.out.println(1);
                else
                    System.out.println(0);
                continue;
            }
            goNextStep(1, 1);
            System.out.println(total);
        }
    }

    static void goNextStep(long len, int step) {
        if (step == x) {
            count(len);
            return;
        }

        long val = (1l << len) - 1;

        while(val < 63) {
            goNextStep(val, step+1);
            val = nextNum(val);
        }
    }

    static long nextNum(long val) {
        long mask = 1l;
        while((mask & val) == 0)
            mask = mask << 1;
        while((mask & val) > 0)
            mask = mask << 1;
        val = val | mask;
        mask = mask >> 1;
        val = val ^ mask;

        return val;
    }

    static void count(long len) {
        long val = (1l << len) - 1;

        while(val <= r) {
            if(val >= l) {
                total++;
            }
            val = nextNum(val);
        }
    }
}
