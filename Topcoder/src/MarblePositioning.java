import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class MarblePositioning {
    double min = Double.MAX_VALUE;

    public double totalWidth(int[] r) {
        min = Double.MAX_VALUE;

        long[] radius = new long[r.length];
        int n = radius.length;
        for (int i = 0; i < n; i++)
            radius[i] = r[i];

        perm(radius, 0);

        return min;
    }

    void perm(long[] r, int index) {
        if (index == r.length) {
            min = Math.min(min, dist(r));
            return;
        }
        for (int i = index; i < r.length; i++) {
            long x = r[index];
            r[index] = r[i];
            r[i] = x;
            perm(r, index + 1);
            x = r[index];
            r[index] = r[i];
            r[i] = x;
        }
    }

    double helper(long a, long b) {
        return 2 * Math.sqrt(a * b);
    }

    double dist(long[] b) {
        long[] r = new long[b.length];
        for(int i = 0; i < r.length; i++)
            r[i] = b[i];

        for (int i = 0; i < r.length - 2; i++) {
            if (r[i] == 0) continue;
            double total = helper(r[i], r[i + 1]);
            for (int j = i + 2; j < r.length; j++) {
                if (r[j] == 0) continue;
                total += helper(r[j - 1], r[j]);
                double dist = helper(r[i], r[j]);
                if (total <= dist) {
                    for (int k = i + 1; k < j; k++)
                        r[k] = 0;
                    total = dist;
                }
            }
        }

        long pre = r[0];
        double ret = 0.0;
        for (int i = 1; i < r.length; i++) {
            if (r[i] == 0) continue;
            ret += helper(pre, r[i]);
            pre = r[i];
        }

        return ret;
    }

    public static void main(String[] args) {
        long time;
        double answer;
        boolean errors = false;
        double desiredAnswer;


        time = System.currentTimeMillis();
        answer = new MarblePositioning().totalWidth(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 50.58155219373389;
        System.out.println("Your answer:");
        System.out.println("\t" + answer);
        System.out.println("Desired answer:");
        System.out.println("\t" + desiredAnswer);
        if (answer != desiredAnswer) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new MarblePositioning().totalWidth(new int[]{7, 7, 7});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 28.0D;
        System.out.println("Your answer:");
        System.out.println("\t" + answer);
        System.out.println("Desired answer:");
        System.out.println("\t" + desiredAnswer);
        if (answer != desiredAnswer) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new MarblePositioning().totalWidth(new int[]{10, 20, 30});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 62.92528739883945D;
        System.out.println("Your answer:");
        System.out.println("\t" + answer);
        System.out.println("Desired answer:");
        System.out.println("\t" + desiredAnswer);
        if (answer != desiredAnswer) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new MarblePositioning().totalWidth(new int[]{100, 100, 11, 11, 25});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 200.0D;
        System.out.println("Your answer:");
        System.out.println("\t" + answer);
        System.out.println("Desired answer:");
        System.out.println("\t" + desiredAnswer);
        if (answer != desiredAnswer) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new MarblePositioning().totalWidth(new int[]{903214, 252429, 550970, 58607, 889016, 695517});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 4430821.778043712D;
        System.out.println("Your answer:");
        System.out.println("\t" + answer);
        System.out.println("Desired answer:");
        System.out.println("\t" + desiredAnswer);
        if (answer != desiredAnswer) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();


        if (errors)
            System.out.println("Some of the test cases had errors :-(");
        else
            System.out.println("You're a stud (at least on the test data)! :-D ");
    }

}
//Powered by [KawigiEdit] 2.0!
