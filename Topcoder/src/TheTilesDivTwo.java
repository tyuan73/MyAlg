public class TheTilesDivTwo {
    static int n;
    static int m;
    static String[] b;
    static boolean[][] occupied;
    static int MAX;

    public int find(String[] board) {
        int max = 0;
        n = board.length;
        m = board[0].length();
        b = board;
        occupied = new boolean[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) {
            occupied[i][0] = true;
            occupied[i][m+1] = true;
        }
        for (int i = 0; i < m + 1; i++) {
            occupied[0][i] = true;
            occupied[n + 1][i] = true;
        }

        MAX = 0;
        for (int i = 1; i <= n; i++) {
            String s = b[i - 1];
            for (int j = 1; j <= m; j++) {
                if (s.charAt(j - 1) == 'X')
                    occupied[i][j] = true;
            }
        }

        return getMax(1, 1);
    }

    int getMax(int i, int j) {
        if (j > m) {
            if (i < n) {
                i++;
                j = 1 + (i%2);
            } else
                return 0;
        }

        int max = 0;
        if (occupied[i][j])
            return getMax(i, j + 2);

        max = getMax(i, j + 2);
        if (!occupied[i - 1][j] && !occupied[i][j + 1]) {
            occupied[i - 1][j] = true;
            occupied[i][j + 1] = true;
            max = Math.max(max, 1 + getMax(i, j + 2));
            occupied[i - 1][j] = false;
            occupied[i][j + 1] = false;
        }
        if (!occupied[i][j + 1] && !occupied[i + 1][j]) {
            occupied[i][j + 1] = true;
            occupied[i + 1][j] = true;
            max = Math.max(max, 1 + getMax(i, j + 2));
            occupied[i][j + 1] = false;
            occupied[i + 1][j] = false;
        }
        if (!occupied[i + 1][j] && !occupied[i][j - 1]) {
            occupied[i + 1][j] = true;
            occupied[i][j - 1] = true;
            max = Math.max(max, 1 + getMax(i, j + 2));
            occupied[i + 1][j] = false;
            occupied[i][j - 1] = false;
        }
        if (!occupied[i][j - 1] && !occupied[i - 1][j]) {
            occupied[i][j - 1] = true;
            occupied[i - 1][j] = true;
            max = Math.max(max, 1 + getMax(i, j + 2));
            occupied[i][j - 1] = false;
            occupied[i - 1][j] = false;
        }

        return max;
    }

    public static void main(String[] args) {
        long time;
        int answer;
        boolean errors = false;
        int desiredAnswer;


        time = System.currentTimeMillis();
        answer = new TheTilesDivTwo().find(new String[]{"X.X", "...", "X.X"});
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
        answer = new TheTilesDivTwo().find(new String[]{"...............................................", "...............................................", "..............................................."});
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
        answer = new TheTilesDivTwo().find(new String[]{"......X.X.XXX.X.XX."});
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
        answer = new TheTilesDivTwo().find(new String[]{"X.....XXX.XX..XXXXXXXXX...X.XX.XX....X", ".XXXX..X..XXXXXXXX....XX.X.X.X.....XXX", "....XX....X.XX..X.X...XX.X..XXXXXXX..X", "XX.XXXXX.X.X..X..XX.XXX..XX...XXX.X..."});
        System.out.println("Time: " + (System.currentTimeMillis() - time) / 1000.0 + " seconds");
        desiredAnswer = 13;
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
