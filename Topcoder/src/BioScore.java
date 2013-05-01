import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class BioScore {
    public double maxAvg(String[] knownSet) {
        char[] map = new char[26];
        map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['T' - 'A'] = 2;
        map['G' - 'A'] = 3;

        int n = knownSet.length;

        int[][] freq = new int[4][4];
        for (int i = 0; i < n - 1; i++) {
            String s1 = knownSet[i];
            for (int j = i + 1; j < n; j++) {
                String s2 = knownSet[j];
                for (int k = 0; k < s1.length(); k++) {
                    int x = map[s1.charAt(k) - 'A'];
                    int y = map[s2.charAt(k) - 'A'];
                    if (x > y) {
                        int t = x;
                        x = y;
                        y = t;
                    }
                    freq[x][y]++;
                }
            }
        }

        int[] f = new int[10];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j <= 3; j++)
                f[index++] = freq[i][j];
        }
        Arrays.sort(f);
        f[0] = freq[0][0];
        f[1] = freq[1][1];
        f[2] = freq[2][2];
        f[3] = freq[3][3];
        for (int i = 4, j = 9; i < j; i++, j--) {
            int x = f[i];
            f[i] = f[j];
            f[j] = x;
        }

        int[] m = new int[10];
        m[4] = m[5] = 10;
        m[7] = m[8] = m[9] = -10;
        int sum = Integer.MIN_VALUE;
        for (int i = 1; i <= 10; i++) {
            m[0] = i;
            for (int j = 1; j <= 10; j++) {
                m[1] = j;
                for (int p = 1; p <= 10; p++) {
                    m[2] = p;
                    for (int q = 1; q <= 10; q++) {
                        m[3] = q;
                        if ((m[0] + m[1] + m[2] + m[3]) % 2 == 0) {
                            m[6] = 10 - (m[0] + m[1] + m[2] + m[3]) / 2;
                            sum = Math.max(sum, getValue(f, m));
                        }
                    }
                }
            }
        }

        return sum / (double) n / (double) (n - 1) * 2;
    }

    static int getValue(int[] f, int[] m) {
        int total = 0;
        for (int i = 0; i < f.length; i++)
            total += f[i] * m[i];
        return total;
    }

    public static void main(String[] args) {
        long time;
        double answer;
        boolean errors = false;
        double desiredAnswer;


        time = System.currentTimeMillis();
        answer = new BioScore().maxAvg(new String[]{"AAA", "AAA", "AAC"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 30.0D;
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
        answer = new BioScore().maxAvg(new String[]{"ACTGACTGACTG", "GACTTGACCTGA"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = -4.0D;
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
        answer = new BioScore().maxAvg(new String[]{"ACTAGAGAC", "AAAAAAAAA", "TAGTCATAC", "GCAGCATTC"});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 50.5D;
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
