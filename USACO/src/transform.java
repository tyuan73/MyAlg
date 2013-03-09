/**
 ID: tyuan731
 LANG: JAVA
 TASK: transform
 **/

import java.io.*;

public class transform {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

        int n = Integer.parseInt(f.readLine());
        boolean[][] m1 = new boolean[n][n];
        int t1 = 0;
        boolean[][] m2 = new boolean[n][n];
        int t2 = 0;
        for (int i = 0; i < n; i++) {
            String line = f.readLine();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == '@') {
                    m1[i][j] = true;
                    t1++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            String line = f.readLine();
            for (int j = 0; j < n; j++) {
                if (line.charAt(j) == '@') {
                    m2[i][j] = true;
                    t2++;
                }
            }
        }

        int step = 7;
        if (t1 == t2) {
            for (int i = 1; i < 4; i++) {
                m1 = rotate(m1);
                if (isSame(m1, m2)) {
                    step = i;
                    break;
                }
            }

            if (step == 7) {
                m1 = reflect(rotate(m1));
                if (isSame(m1, m2)) {
                    step = 4;
                } else {
                    for (int i = 1; i < 4; i++) {
                        m1 = rotate(m1);
                        if (isSame(m1, m2)) {
                            step = 5;
                            break;
                        }
                    }
                    if(step == 7) {
                        m1 = reflect(rotate(m1));
                        if(isSame(m1, m2))
                            step = 6;
                    }
                }
            }
        }

        out.println(step);

        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static boolean isSame(boolean[][] m1, boolean[][] m2) {
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1.length; j++) {
                if (m1[i][j] != m2[i][j])
                    return false;
            }
        }
        return true;
    }

    // rotate 90 degree clockwise
    static boolean[][] rotate(boolean[][] m1) {
        int n = m1.length;
        boolean[][] ret = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (m1[i][j]) {
                    ret[j][n - 1 - i] = true;
                }
            }
        }
        return ret;
    }

    // reflect upside down
    static boolean[][] reflect(boolean[][] m1) {
        int n = m1.length;
        boolean[][] ret = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (m1[i][j])
                    ret[i][n-1-j] = true;
            }
        }
        return ret;
    }
}
