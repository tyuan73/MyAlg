import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

/**
 * The question:
 * http://community.topcoder.com/stat?c=problem_statement&pm=12496&rd=15495
 *
 * The answer:
 * http://apps.topcoder.com/wiki/display/tc/SRM+575
 */
public class TheNumberGameDivOne {
    public String find(long n) {
        boolean flag = false;
        if ((n & 1) == 0) {
            flag = true;
            if ((n & (n - 1)) == 0) {
                while(n > 1) {
                    flag = !flag;
                    n >>= 1;
                }
            }
        }
        return flag ? "John" : "Brus";
    }

    public static void main(String[] args) {
        long time;
        String answer;
        boolean errors = false;
        String desiredAnswer;


        time = System.currentTimeMillis();
        answer = new TheNumberGameDivOne().find(6L);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = "John";
        System.out.println("Your answer:");
        System.out.println("\t\"" + answer + "\"");
        System.out.println("Desired answer:");
        System.out.println("\t\"" + desiredAnswer + "\"");
        if (!answer.equals(desiredAnswer)) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new TheNumberGameDivOne().find(2L);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = "Brus";
        System.out.println("Your answer:");
        System.out.println("\t\"" + answer + "\"");
        System.out.println("Desired answer:");
        System.out.println("\t\"" + desiredAnswer + "\"");
        if (!answer.equals(desiredAnswer)) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new TheNumberGameDivOne().find(747L);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = "Brus";
        System.out.println("Your answer:");
        System.out.println("\t\"" + answer + "\"");
        System.out.println("Desired answer:");
        System.out.println("\t\"" + desiredAnswer + "\"");
        if (!answer.equals(desiredAnswer)) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new TheNumberGameDivOne().find(128L);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = "Brus";
        System.out.println("Your answer:");
        System.out.println("\t\"" + answer + "\"");
        System.out.println("Desired answer:");
        System.out.println("\t\"" + desiredAnswer + "\"");
        if (!answer.equals(desiredAnswer)) {
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
