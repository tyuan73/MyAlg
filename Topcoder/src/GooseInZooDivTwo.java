import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class GooseInZooDivTwo {
    int[][] g = null;
    int n = 0;
    int m = 0;
    int d = 0;
    static final int MOD = 1000000007;

    public int count(String[] field, int dist) {
        n = field.length;
        m = field[0].length();
        d = dist;
        g = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                char ch = field[i].charAt(j);
                if (ch == 'v')
                    g[i][j] = 1;
            }

        int com = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 1)
                    dfs(i, j, com++);
            }
        }
        com -= 2;
        long res = 1;
        for (int i = 0; i < com; i++)
            res = (res * 2) % MOD;

        return (int) res - 1;
    }

    void dfs(int i, int j, int c) {
        if (i < 0 || i >= n || j < 0 || j >= m || g[i][j] != 1)
            return;
        g[i][j] = c;
        for (int k = i - d; k <= i + d; k++) {
            int x = d - Math.abs(k - i);
            for (int l = j - x; l <= j + x; l++) {
                dfs(k, l, c);
            }
        }
    }

    public static void main(String[] args) {
        long time;
        int answer;
        boolean errors = false;
        int desiredAnswer;


        time = System.currentTimeMillis();
        answer = new GooseInZooDivTwo().count(new String[]{"vvv"}, 0);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 7;
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
        answer = new GooseInZooDivTwo().count(new String[]{"."}, 100);
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
        answer = new GooseInZooDivTwo().count(new String[]{"vvv"}, 1);
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
        answer = new GooseInZooDivTwo().count(new String[]{"v.v..................v............................", ".v......v..................v.....................v", "..v.....v....v.........v...............v......v...", ".........vvv...vv.v.........v.v..................v", ".....v..........v......v..v...v.......v...........", "...................vv...............v.v..v.v..v...", ".v.vv.................v..............v............", "..vv.......v...vv.v............vv.....v.....v.....", "....v..........v....v........v.......v.v.v........", ".v.......v.............v.v..........vv......v.....", "....v.v.......v........v.....v.................v..", "....v..v..v.v..............v.v.v....v..........v..", "..........v...v...................v..............v", "..v........v..........................v....v..v...", "....................v..v.........vv........v......", "..v......v...............................v.v......", "..v.v..............v........v...............vv.vv.", "...vv......v...............v.v..............v.....", "............................v..v.................v", ".v.............v.......v..........................", "......v...v........................v..............", ".........v.....v..............vv..................", "................v..v..v.........v....v.......v....", "........v.....v.............v......v.v............", "...........v....................v.v....v.v.v...v..", "...........v......................v...v...........", "..........vv...........v.v.....................v..", ".....................v......v............v...v....", ".....vv..........................vv.v.....v.v.....", ".vv.......v...............v.......v..v.....v......", "............v................v..........v....v....", "................vv...v............................", "................v...........v........v...v....v...", "..v...v...v.............v...v........v....v..v....", "......v..v.......v........v..v....vv..............", "...........v..........v........v.v................", "v.v......v................v....................v..", ".v........v................................v......", "............................v...v.......v.........", "........................vv.v..............v...vv..", ".......................vv........v.............v..", "...v.............v.........................v......", "....v......vv...........................v.........", "....vv....v................v...vv..............v..", "..................................................", "vv........v...v..v.....v..v..................v....", ".........v..............v.vv.v.............v......", ".......v.....v......v...............v.............", "..v..................v................v....v......", ".....v.....v.....................v.v......v......."}, 3);
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 797922654;
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
