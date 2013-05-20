import java.util.*;

public class PalindromeGame {
    class Ele implements Comparable<Ele> {
        String s;
        int v;

        Ele(String str, int val) {
            this.s = str;
            this.v = val;
        }

        public int compareTo(Ele e) {
            return e.v - this.v;
        }
    }

    public int getMaximum(String[] front, int[] back) {
        int n = front.length;
        int total = 0;

        Ele[] all = new Ele[n];
        for (int i = 0; i < n; i++) {
            all[i] = new Ele(front[i], back[i]);
        }

        Arrays.sort(all);

        boolean[] chosen = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (chosen[i]) continue;
            String s1 = all[i].s;
            for (int j = i + 1; j < n; j++) {
                if (chosen[j]) continue;
                if (isPalindrome(s1, all[j].s)) {
                    chosen[i] = chosen[j] = true;
                    total += all[i].v + all[j].v;
                    break;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (chosen[i]) continue;
            boolean yes = true;
            for (int l = 0, r = front[i].length() - 1; l < r; l++, r--) {
                if (all[i].s.charAt(l) != all[i].s.charAt(r)) {
                    yes = false;
                    break;
                }
            }
            if (yes)
                max = Math.max(max, all[i].v);
        }

        return total + max;
    }

    boolean isPalindrome(String s1, String s2) {
        for (int l = 0, r = s1.length() - 1; r >= 0; l++, r--) {
            if (s1.charAt(l) != s2.charAt(r))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        long time;
        int answer;
        boolean errors = false;
        int desiredAnswer;


        time = System.currentTimeMillis();
        answer = new PalindromeGame().getMaximum(new String[]{"topcoder", "redcoder", "redocpot"}, new int[]{7, 5, 3});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 10;
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
        answer = new PalindromeGame().getMaximum(new String[]{"rabbit"}, new int[]{1000000});
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
        answer = new PalindromeGame().getMaximum(new String[]{"abc", "abc", "def", "cba", "fed"}, new int[]{24, 7, 63, 222, 190});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 499;
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
