import java.util.*;
import java.util.regex.*;
import java.text.*;
import java.math.*;
import java.awt.geom.*;

public class WorldPeace {
    public long numGroups(int k, int[] countries) {
        Arrays.sort(countries);
        for(int i : countries)
        System.out.printf("%5d", i);
        System.out.println();

        int n = countries.length;
        int start = 0;
        long total = 0;
        for(int i = 0; i <= n-k; i++) {

        }

        return total;
    }

    /**
     * ********
     * public long numGroups(int k, int[] countries) {
     * Arrays.sort(countries);
     * int n = countries.length;
     * if (n == k)
     * return countries[0];
     * <p/>
     * long total = 0;
     * while (true) {
     * if (countries[n - k] <= 0)
     * break;
     * <p/>
     * int count = countries[n - k];
     * count = (count + 999) / 1000;
     * total += count;
     * for (int i = n - k; i < n; i++)
     * countries[i] -= count;
     * <p/>
     * Arrays.sort(countries);
     * }
     * return total;
     * }
     * *********
     */

    public static void main(String[] args) {
        long time;
        long answer;
        boolean errors = false;
        long desiredAnswer;


        time = System.currentTimeMillis();
        answer = new WorldPeace().numGroups(4, new int[]{4, 4, 4, 4, 4});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 5L;
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
        answer = new WorldPeace().numGroups(5, new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 3L;
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
        answer = new WorldPeace().numGroups(2, new int[]{1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 3000000000L;
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
        answer = new WorldPeace().numGroups(7, new int[]{96, 17, 32, 138, 112, 50, 7, 19, 412, 23, 14, 50, 47, 343, 427, 22, 39});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 166L;
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
        answer = new WorldPeace().numGroups(10, new int[]{638074479, 717901019, 910893151, 924124222, 991874870, 919392444, 729973192, 607898881, 838529741, 907090878, 632877562, 678638852, 749258866, 949661738, 784641190, 815740520, 689809286, 711327114, 658017649, 636727234, 871088534, 964608547, 867960437, 964911023, 642411618, 868318236, 793328473, 849540177, 960039699, 998262224, 775720601, 634685437, 743766982, 826321850, 846671921, 712570181, 676890302, 814283264, 958273130, 899003369, 909973864, 921987721, 978601888, 633027021, 896400011, 725078407, 662183572, 629843174, 617774786, 695823011});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 3983180234L;
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
