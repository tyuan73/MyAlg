import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class GoldMine {
    int max = Integer.MIN_VALUE;
    int miners = 0;
    int n = 0;
    int[][] v = null;
    int[] ret = null;

    public int[] getAllocation(String[] mines, int miners) {
        max = Integer.MIN_VALUE;
        this.miners = miners;

        n = mines.length;
        int[][] m = new int[n][7];
        v = new int[n][7];
        for (int i = 0; i < n; i++) {
            String s = mines[i];
            String[] ss = s.split(", ");
            for (int j = 0; j < 7; j++)
                m[i][j] = Integer.parseInt(ss[j]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < 7; j++) {
                int t = 0;
                for (int k = 0; k < 7; k++) {
                    if (m[i][k] == 0) continue;
                    if (j < k) {
                        t += j * 60 * m[i][k];
                    } else if (j == k) {
                        t += j * 50 * m[i][k];
                    } else {
                        t += (k * 50 - (j - k) * 20) * m[i][k];
                    }
                }
                v[i][j] = t;
            }
        }

        /** recursive version -- it works, but very slow.
         ret = new int[n];
         search(0, 0, 0, new int[n]);
         return ret;
         */

        ret = new int[n];
        for (int i = 0; i < miners; i++) {
            int best = Integer.MIN_VALUE;
            int idx = -1;
            for (int j = 0; j < n; j++) {
                if (ret[j] < 6) {
                    if (v[j][ret[j] + 1] - v[j][ret[j]] > best) {
                        best = v[j][ret[j] + 1] - v[j][ret[j]];
                        idx = j;
                    }
                }
            }
            ret[idx]++;
        }

        return ret;
    }

    void search(int index, int totalM, int totalValue, int[] path) {
        if (index >= n) {
            if (totalM == miners && totalValue > max) {
                max = totalValue;
                for (int i = 0; i < n; i++)
                    ret[i] = path[i];
            }
            return;
        }

        int x = Math.min(miners - totalM, 6);
        for (int i = x; i >= 0; i--) {
            if (miners - totalM - i > (n - index - 1) * 6)
                return;
            path[index] = i;
            search(index + 1, totalM + i, totalValue + v[index][i], path);
        }
    }

    public static void main(String[] args) {
        long time;
        int[] answer;
        boolean errors = false;
        int[] desiredAnswer;

        boolean same;

        time = System.currentTimeMillis();
        answer = new GoldMine().getAllocation(new String[]{"000, 030, 030, 040, 000, 000, 000", "020, 020, 020, 010, 010, 010, 010"}, 4);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = new int[]{2, 2};
        System.out.println("Your answer:");
        if (answer.length > 0) {
            System.out.print("\t{ " + answer[0]);
            for (int i = 1; i < answer.length; i++)
                System.out.print(", " + answer[i]);
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        System.out.println("Desired answer:");
        if (desiredAnswer.length > 0) {
            System.out.print("\t{ " + desiredAnswer[0]);
            for (int i = 1; i < desiredAnswer.length; i++)
                System.out.print(", " + desiredAnswer[i]);
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        same = desiredAnswer.length == answer.length;
        for (int i = 0; i < answer.length && same; i++)
            if (answer[i] != desiredAnswer[i])
                same = false;
        if (!same) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new GoldMine().getAllocation(new String[]{"100, 000, 000, 000, 000, 000, 000", "100, 000, 000, 000, 000, 000, 000", "100, 000, 000, 000, 000, 000, 000", "100, 000, 000, 000, 000, 000, 000", "100, 000, 000, 000, 000, 000, 000"}, 8);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = new int[]{6, 2, 0, 0, 0};
        System.out.println("Your answer:");
        if (answer.length > 0) {
            System.out.print("\t{ " + answer[0]);
            for (int i = 1; i < answer.length; i++)
                System.out.print(", " + answer[i]);
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        System.out.println("Desired answer:");
        if (desiredAnswer.length > 0) {
            System.out.print("\t{ " + desiredAnswer[0]);
            for (int i = 1; i < desiredAnswer.length; i++)
                System.out.print(", " + desiredAnswer[i]);
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        same = desiredAnswer.length == answer.length;
        for (int i = 0; i < answer.length && same; i++)
            if (answer[i] != desiredAnswer[i])
                same = false;
        if (!same) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new GoldMine().getAllocation(new String[]{"050, 000, 000, 000, 000, 050, 000", "050, 000, 000, 000, 000, 050, 000", "050, 000, 000, 000, 000, 050, 000", "050, 000, 000, 000, 000, 050, 000", "050, 000, 000, 000, 000, 050, 000", "050, 000, 000, 000, 000, 050, 000", "050, 000, 000, 000, 000, 050, 000", "050, 000, 000, 000, 000, 050, 000", "050, 000, 000, 000, 000, 050, 000", "050, 000, 000, 000, 000, 050, 000"}, 30);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = new int[]{4, 4, 4, 4, 4, 4, 4, 2, 0, 0};
        System.out.println("Your answer:");
        if (answer.length > 0) {
            System.out.print("\t{ " + answer[0]);
            for (int i = 1; i < answer.length; i++)
                System.out.print(", " + answer[i]);
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        System.out.println("Desired answer:");
        if (desiredAnswer.length > 0) {
            System.out.print("\t{ " + desiredAnswer[0]);
            for (int i = 1; i < desiredAnswer.length; i++)
                System.out.print(", " + desiredAnswer[i]);
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        same = desiredAnswer.length == answer.length;
        for (int i = 0; i < answer.length && same; i++)
            if (answer[i] != desiredAnswer[i])
                same = false;
        if (!same) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new GoldMine().getAllocation(new String[]{"026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004", "026, 012, 005, 013, 038, 002, 004"}, 56);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = new int[]{2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println("Your answer:");
        if (answer.length > 0) {
            System.out.print("\t{ " + answer[0]);
            for (int i = 1; i < answer.length; i++)
                System.out.print(", " + answer[i]);
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        System.out.println("Desired answer:");
        if (desiredAnswer.length > 0) {
            System.out.print("\t{ " + desiredAnswer[0]);
            for (int i = 1; i < desiredAnswer.length; i++)
                System.out.print(", " + desiredAnswer[i]);
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        same = desiredAnswer.length == answer.length;
        for (int i = 0; i < answer.length && same; i++)
            if (answer[i] != desiredAnswer[i])
                same = false;
        if (!same) {
            errors = true;
            System.out.println("DOESN'T MATCH!!!!");
        } else
            System.out.println("Match :-)");
        System.out.println();
        time = System.currentTimeMillis();
        answer = new GoldMine().getAllocation(new String[]{"100, 000, 000, 000, 000, 000, 000", "090, 010, 000, 000, 000, 000, 000", "080, 020, 000, 000, 000, 000, 000", "075, 025, 000, 000, 000, 000, 000", "050, 050, 000, 000, 000, 000, 000", "025, 075, 000, 000, 000, 000, 000", "020, 080, 000, 000, 000, 000, 000", "010, 090, 000, 000, 000, 000, 000", "000, 100, 000, 000, 000, 000, 000", "000, 090, 010, 000, 000, 000, 000", "000, 080, 020, 000, 000, 000, 000", "000, 075, 025, 000, 000, 000, 000", "000, 050, 050, 000, 000, 000, 000", "000, 025, 075, 000, 000, 000, 000", "000, 020, 080, 000, 000, 000, 000", "000, 010, 090, 000, 000, 000, 000", "000, 000, 100, 000, 000, 000, 000", "000, 000, 090, 010, 000, 000, 000", "000, 000, 080, 020, 000, 000, 000", "000, 000, 075, 025, 000, 000, 000", "000, 000, 050, 050, 000, 000, 000", "000, 000, 025, 075, 000, 000, 000", "000, 000, 020, 080, 000, 000, 000", "000, 000, 010, 090, 000, 000, 000", "000, 000, 000, 100, 000, 000, 000", "000, 000, 000, 100, 000, 000, 000", "000, 000, 000, 090, 010, 000, 000", "000, 000, 000, 080, 020, 000, 000", "000, 000, 000, 075, 025, 000, 000", "000, 000, 000, 050, 050, 000, 000", "000, 000, 000, 025, 075, 000, 000", "000, 000, 000, 020, 080, 000, 000", "000, 000, 000, 010, 090, 000, 000", "000, 000, 000, 000, 100, 000, 000", "000, 000, 000, 000, 090, 010, 000", "000, 000, 000, 000, 080, 020, 000", "000, 000, 000, 000, 075, 025, 000", "000, 000, 000, 000, 050, 050, 000", "000, 000, 000, 000, 025, 075, 000", "000, 000, 000, 000, 020, 080, 000", "000, 000, 000, 000, 010, 090, 000", "000, 000, 000, 000, 000, 100, 000", "000, 000, 000, 000, 000, 090, 010", "000, 000, 000, 000, 000, 080, 020", "000, 000, 000, 000, 000, 075, 025", "000, 000, 000, 000, 000, 050, 050", "000, 000, 000, 000, 000, 025, 075", "000, 000, 000, 000, 000, 020, 080", "000, 000, 000, 000, 000, 010, 090", "000, 000, 000, 000, 000, 000, 100"}, 150);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = new int[]{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 6, 6};
        System.out.println("Your answer:");
        if (answer.length > 0) {
            System.out.print("\t{ " + answer[0]);
            for (int i = 1; i < answer.length; i++)
                System.out.print(", " + answer[i]);
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        System.out.println("Desired answer:");
        if (desiredAnswer.length > 0) {
            System.out.print("\t{ " + desiredAnswer[0]);
            for (int i = 1; i < desiredAnswer.length; i++)
                System.out.print(", " + desiredAnswer[i]);
            System.out.println(" }");
        } else
            System.out.println("\t{ }");
        same = desiredAnswer.length == answer.length;
        for (int i = 0; i < answer.length && same; i++)
            if (answer[i] != desiredAnswer[i])
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
