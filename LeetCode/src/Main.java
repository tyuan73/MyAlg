public class Main {

    static long total = 0;

    public static void main(String[] args) {
        int n = 2;
        int m = 6;

        //int[][] table = new int[n][m];
        int[] rows = new int[n];
        //table[0][0] = 1; table[n-1][m-1] = n*m;
        rows[0] = 1;
        helper(rows, 2, m, n * m);
        System.out.println(total);

        /*
        int max = 0;
        for(int x = 1000000000; x >= 900000000; x--) {
            int t = 0;
            for(int i = 1; i <= Math.sqrt(x); i++)
                if((x%i) == 0)   {
                    t++;
                }
            max = Math.max(max, t);
        }
        System.out.println(max);
        */
    }

    static void helper(int[] rows, int next, int m, int max) {
        if (next == max) {
            /*
            for(int[] x : table) {
                for(int y : x)
                    System.out.printf("%5d", y);
                System.out.println();
            }
            System.out.println();
            */
            total++;

            return;
        }

        for (int i = 0; i < rows.length; i++) {
            if (rows[i] == m || (i > 0 && rows[i] == rows[i - 1])) continue;
            //table[i][rows[i]] = next;
            rows[i]++;
            helper(rows, next + 1, m, max);
            rows[i]--;
        }
    }
        /*
        int[] a = {-1,0,3,4,5,5,5,5,5,5,6};
        int next = 1;
        for(int i = 1; i < a.length; i++) {
            if(a[i] != a[i-1]) {
                a[next++] = a[i];
            }
        }

        for(int i : a) {
            System.out.printf("%5d", i);
        }
        System.out.println();
        */

}
