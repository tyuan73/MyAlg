import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class TimeTravellingGardener {
    public int determineUsage(int[] distance, int[] height) {
        int[] leftward = new int[distance.length + 1];
        for (int i = 0; i < distance.length; i++) {
            leftward[i + 1] = leftward[i] + distance[i];
        }

        int min = getMin(leftward, height);

        for (int i = 0, j = height.length - 1; i < j; i++, j--) {
            int x = height[i];
            height[i] = height[j];
            height[j] = x;
        }
        for (int i = distance.length - 1; i >= 0; i--) {
            leftward[distance.length - i] = leftward[distance.length - i - 1] + distance[i];
        }

        min = Math.min(min, getMin(leftward, height));

        return min;
    }

    int getMin(int[] leftward, int[] height) {
        //printArray(leftward);
        //printArray(height);
        int ret = height.length - 1;
        for (int i = 0; i < height.length; i++) {
            double h1 = height[i];
            double minX = leftward[i] - leftward[0];
            double x = 0.0;
            boolean isOK = true;
            for (int j = i + 1; j < height.length; j++) {
                int h2 = height[j];
                if (h2 < height[i]) {
                    isOK = false;
                    break;
                }
                if (h2 == height[i]) {
                    x = Double.MAX_VALUE;
                    break;
                } else {
                    x = Math.max(x, h1 / (h2 - h1) * (double) (leftward[j] - leftward[i]));
                }
            }

            if (isOK && x >= minX) {
                int curMin = 0;
                for (int j = i + 1; j < height.length; j++) {
                    int h2 = height[j];
                    if (h2 == height[i]) continue;
                    double curX = h1 / (h2 - h1) * (double) (leftward[j] - leftward[i]);
                    if (Math.abs(x - curX) > 0.001) curMin++;
                }
                ret = Math.min(ret, curMin + i);
            }
        }
        return ret;
    }

    private void printArray(int[] a) {
        for (int i : a)
            System.out.printf("%8d", i);
        System.out.println();
    }


    /*
        public static void main(String[] args) {
            int[] d = {2,4,2,2,4,2,4,2,2,4};
            int[] h = {2,2,10,10,10,16,16,22,22,28,28};
            new TimeTravellingGardener().determineUsage(d, h);
        }
    */
    public static void main(String[] args) {
        long time;
        int answer;
        boolean errors = false;
        int desiredAnswer;


        time = System.currentTimeMillis();
        answer = new TimeTravellingGardener().determineUsage(new int[]{2, 2}, new int[]{1, 3, 10});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 1;
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
        answer = new TimeTravellingGardener().determineUsage(new int[]{3, 3}, new int[]{3, 1, 3});
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
        answer = new TimeTravellingGardener().determineUsage(new int[]{1, 3}, new int[]{4, 4, 4});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 0;
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
        answer = new TimeTravellingGardener().determineUsage(new int[]{4, 2}, new int[]{9, 8, 5});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 1;
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
        answer = new TimeTravellingGardener().determineUsage(new int[]{476, 465, 260, 484}, new int[]{39, 13, 8, 72, 80});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 3;
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
        answer = new TimeTravellingGardener().determineUsage(new int[]{173, 36, 668, 79, 26, 544}, new int[]{488, 743, 203, 446, 444, 91, 453});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 5;
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
        answer = new TimeTravellingGardener().determineUsage(new int[]{2, 4, 2, 2, 4, 2, 4, 2, 2, 4}, new int[]{2, 2, 10, 10, 10, 16, 16, 22, 22, 28, 28});
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


        if (errors)
            System.out.println("Some of the test cases had errors :-(");
        else
            System.out.println("You're a stud (at least on the test data)! :-D ");
    }
}
//Powered by [KawigiEdit] 2.0!
