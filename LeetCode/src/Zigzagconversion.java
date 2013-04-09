/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/26/13
 * Time: 1:37 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class ZigzagConversion {
    /**
     * Naive solution.
     *
     * @param s
     * @param nRows
     * @return
     */
    public String convert1(String s, int nRows) {
        int n = s.length();

        char[][] b = new char[nRows][n];
        int col = 0, j, i = 0;
        while (i < s.length()) {
            for (j = 0; j < nRows && i < s.length(); j++) {
                b[j][col] = s.charAt(i++);
            }
            for (j = nRows - 2, col = col + 1; j >= 1 && i < s.length(); j--, col++) {
                b[j][col] = s.charAt(i++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (i = 0; i < nRows; i++)
            for (j = 0; j < n; j++)
                if (b[i][j] != 0)
                    sb.append(b[i][j]);

        return sb.toString();
    }

    public String convert(String s, int nRows) {
        int n = s.length();

        if (nRows == 1)
            return s;

        char[] ret = new char[n];
        int index = 0;
        for (int i = 0; i < nRows; i++) {
            for (int base = 0; base < n + nRows; base += 2 * nRows - 2) {
                if (i != 0 && i != nRows - 1 && base - i > 0 && base - i < n)
                    ret[index++] = s.charAt(base - i);
                if (base + i < n)
                    ret[index++] = s.charAt(base + i);
            }
        }
        return new String(ret);
    }

    public static void main(String[] args) {
        ZigzagConversion zz = new ZigzagConversion();
        System.out.println(zz.convert("ABCD", 3));
    }
}
