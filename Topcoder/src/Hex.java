import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class Hex {
    public String[] picture(int n, String[] marks) {
        char[][] map = new char[3 * n][4 * n + 3];
        map[0][0] = ' ';
        map[0][1] = '_';
        for (int i = 1; i < 3 * n; i++) {
            for (int j = 0; j < 4 * n; j += 4) {
                if (i % 2 == 0) {
                    map[i][j] = '\\';
                    map[i][j + 1] = '_';
                    map[i][j + 2] = '/';
                    map[i][j + 3] = ' ';
                } else {
                    map[i][j] = '/';
                    map[i][j + 1] = ' ';
                    map[i][j + 2] = '\\';
                    map[i][j + 3] = '_';
                }
            }
        }

        for (String s : marks) {
            int x = Integer.parseInt(s.substring(0, 1));
            int y = Integer.parseInt(s.substring(1, 2));
            char ch = s.charAt(2);
            y = y * 2 + 1 + x;
            x = x * 2 + 1;

            map[y][x] = ch;
        }

        String[] ret = new String[3 * n];
        StringBuilder sb = new StringBuilder();
        int start = 0, end = 2;
        for (int i = 0; i < 3 * n; i++) {
            if (i > 2 * n) {
                start = (i - 2 * n) * 2;
                for (int j = 0; j < start; j++)
                    sb.append(' ');
            }
            for (int j = start; j < end; j++)
                sb.append(map[i][j]);

            if (i == n - 1)
                end++;
            else if (i < n - 1)
                end += 2;

            ret[i] = sb.toString();
            sb.setLength(0);
        }
        return ret;
    }

    public static void main(String[] args) {
        long time;
        String[] answer;
        boolean errors = false;
        String[] desiredAnswer;

        boolean same;

        time = System.currentTimeMillis();
        answer = new Hex().picture(4, new String[]{"00h", "21h", "01v", "03v", "02v"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = new String[]{" _", "/h\\_", "\\_/ \\_", "/v\\_/ \\_", "\\_/ \\_/ \\", "/v\\_/h\\_/", "\\_/ \\_/ \\", "/v\\_/ \\_/", "\\_/ \\_/ \\", "  \\_/ \\_/", "    \\_/ \\", "      \\_/"};
        System.out.println("Your answer:");
        if (answer.length > 0) {
            System.out.print("\t{ \"" + answer[0] + "\"");
            for (int i = 1; i < answer.length; i++)
                System.out.print(",\n\t  \"" + answer[i] + "\"");
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        System.out.println("Desired answer:");
        if (desiredAnswer.length > 0) {
            System.out.print("\t{ \"" + desiredAnswer[0] + "\"");
            for (int i = 1; i < desiredAnswer.length; i++)
                System.out.print(",\n\t  \"" + desiredAnswer[i] + "\"");
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        same = desiredAnswer.length == answer.length;
        for (int i = 0; i < answer.length && same; i++)
            if (!answer[i].equals(desiredAnswer[i]))
                same = false;
        if (!same) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new Hex().picture(3, new String[]{"00v", "01v", "02v", "11h", "21h"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = new String[]{" _", "/v\\_", "\\_/ \\_", "/v\\_/ \\", "\\_/h\\_/", "/v\\_/h\\", "\\_/ \\_/", "  \\_/ \\", "    \\_/"};
        System.out.println("Your answer:");
        if (answer.length > 0) {
            System.out.print("\t{ \"" + answer[0] + "\"");
            for (int i = 1; i < answer.length; i++)
                System.out.print(",\n\t  \"" + answer[i] + "\"");
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        System.out.println("Desired answer:");
        if (desiredAnswer.length > 0) {
            System.out.print("\t{ \"" + desiredAnswer[0] + "\"");
            for (int i = 1; i < desiredAnswer.length; i++)
                System.out.print(",\n\t  \"" + desiredAnswer[i] + "\"");
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        same = desiredAnswer.length == answer.length;
        for (int i = 0; i < answer.length && same; i++)
            if (!answer[i].equals(desiredAnswer[i]))
                same = false;
        if (!same) {
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
