import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class TheNumberGameDiv2 {
    public int minimumMoves(int A, int B) {
        int base = 1;
        while (base < B) {
            base *= 10;
        }

        int ret = -1;

        int c1 = 0;
        long a = A;
        while (a > 0) {
            if ((a % base) == B) {
                a /= base;
                int c2 = 0;
                while (a > 0) {
                    c2++;
                    a /= 10;
                }
                if (c2 == 0)
                    ret = c1;
                else
                    ret = c1 + c2 + 2;
                break;
            }
            c1++;
            a /= 10;
        }

        a = 0;
        while (A > 0) {
            a = a * 10 + (A % 10);
            A /= 10;
        }
        int ret2 = -1;
        c1 = 0;
        while (a > 0) {
            if ((a % base) == B) {
                a /= base;
                int c2 = 0;
                while (a > 0) {
                    c2++;
                    a /= 10;
                }
                ret2 = c1 + c2 + 1;
                break;
            }
            c1++;
            a /= 10;
        }

        if (ret == -1)
            return ret2;
        if (ret2 == -1)
            return ret;

        return Math.min(ret, ret2);
    }

    public static void main(String[] args) {
        long time;
        int answer;
        boolean errors = false;
        int desiredAnswer;


        time = System.currentTimeMillis();
        answer = new TheNumberGameDiv2().minimumMoves(25, 5);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 2;
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
        answer = new TheNumberGameDiv2().minimumMoves(5162, 16);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 4;
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
        answer = new TheNumberGameDiv2().minimumMoves(334, 12);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = -1;
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
        answer = new TheNumberGameDiv2().minimumMoves(218181918, 9181);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 6;
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
        answer = new TheNumberGameDiv2().minimumMoves(9798147, 79817);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = -1;
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
