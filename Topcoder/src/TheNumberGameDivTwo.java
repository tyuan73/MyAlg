import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class TheNumberGameDivTwo {
    public String find(int n) {
        boolean[] a = new boolean[10001];
        //a[4] = true;
        for (int i = 4; i <= n; i++) {
            for (int x = 2; x < i; x++) {
                if (i % x == 0 && !a[i - x]) {
                    a[i] = true;
                    break;
                }
            }
        }

        return a[n] ? "John" : "Brus";
    }

    public static void main(String[] args) {
        long time;
        String answer;
        boolean errors = false;
        String desiredAnswer;


        time = System.currentTimeMillis();
        answer = new TheNumberGameDivTwo().find(6);
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
        answer = new TheNumberGameDivTwo().find(2);
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
        answer = new TheNumberGameDivTwo().find(747);
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
        answer = new TheNumberGameDivTwo().find(128);
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
