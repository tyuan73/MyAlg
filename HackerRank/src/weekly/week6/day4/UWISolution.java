package weekly.week6.day4;

/**
 * uwi's solution
 * https://www.hackerrank.com/rest/contests/w6/challenges/best-spot/hackers/uwi/download_solution
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class UWISolution {
    static InputStream is;
    static PrintWriter out;
    static String INPUT = "";

    static void solve() {
        int n = ni(), m = ni();
        int[][] a = new int[n][];
        for (int i = 0; i < n; i++) {
            a[i] = na(m);
        }
        int h = ni(), w = ni();
        int[][] b = new int[h][];
        for (int i = 0; i < h; i++) {
            b[i] = na(w);
        }

        int Q = 512;
        int[][] bf = new int[Q][Q];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                bf[n - 1 - i][m - 1 - j] = b[i][j];
            }
        }
        int[][] af = new int[Q][Q];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                af[i][j] = a[i][j];
            }
        }

        // test
        out.println("af");
        for(int x = 0; x < 5; x++) {
            for(int y = 0; y < 5; y++)
                out.print(" " + af[x][y]);
            out.println();
        }
        out.println("bf");
        for(int x = 0; x < 5; x++) {
            for(int y = 0; y < 5; y++)
                out.print(" " + bf[x][y]);
            out.println();
        }


        double[][][] q = convolute(af, bf);

        // test
        /*
        out.println("q");
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 2; y++) {
                out.print(" -- ");
                for(int z = 0; z < 10; z++)
                    out.println(" " + q[x][y][z]);
                out.println();
            }
            out.println();
        }
        */

//		for(double[][] row : q){
//			for(double[] x : row){
//				for(int u = 0;u < x.length;u++){
//					x[u] = Math.round(x[u]);
//				}
//			}
//			tr(row[0]);
//		}
//		int maxr = -1;
//		int maxc = -1;
//		for(int i = 0;i < n;i++){
//			for(int j = 0;j < m;j++){
//				if(Math.round(q[i][0][j]) != 0){
//					maxr = Math.max(maxr, i);
//					maxc = Math.max(maxc, j);
//				}
//			}
//		}
//		tr(maxr, maxc);

        long[][] sq = new long[n + 1][m + 1];
        long basesq = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                basesq += b[i][j] * b[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sq[i + 1][j + 1] = sq[i + 1][j] + sq[i][j + 1] - sq[i][j] + a[i][j] * a[i][j];
            }
        }

        long gmin = Long.MAX_VALUE;
        int besti = -1;
        int bestj = -1;
        for (int i = 0; i < n - h + 1; i++) {
            for (int j = 0; j < m - w + 1; j++) {
                long sum = 0;
                sum -= 2L * Math.round(q[i + n - 1][0][j + m - 1]);
                sum += sq[h + i][w + j] - sq[h + i][j] - sq[i][w + j] + sq[i][j];
                sum += basesq;
                if (sum < gmin) {
                    gmin = sum;
                    besti = i + 1;
                    bestj = j + 1;
                }

                //test
                out.println(" i + n - 1 = " + (i + n - 1) + ", j + m - 1 = " + (j + m - 1) + ", value = " + q[i + n - 1][0][j + m - 1]);
            }
        }
        out.println(gmin);
        out.println(besti + " " + bestj);

//		tr(19*-18+19*-12+5*-10+8*-7);
    }

    public static void fft(double[] re, double[] im, boolean inverse) {
        int n = re.length;
        int h = Integer.numberOfTrailingZeros(n);
        for (int i = 0; i < n; i++) {
            int rev = Integer.reverse(i) >>> 32 - h;
            if (i < rev) {
                double d = re[i];
                re[i] = re[rev];
                re[rev] = d;
                d = im[i];
                im[i] = im[rev];
                im[rev] = d;
            }
        }

        for (int s = 2; s <= n; s <<= 1) {
            int nt = s >>> 1;
            double theta = inverse ? -2 * Math.PI / s : 2 * Math.PI / s;
            double wRe = Math.cos(theta);
            double wIm = Math.sin(theta);
            for (int j = 0; j < n; j += s) {
                double wr = 1, wi = 0;
                for (int t = j; t < j + nt; t++) {
                    int jp = t + nt;
                    double lre = re[jp] * wr - im[jp] * wi;
                    double lim = re[jp] * wi + im[jp] * wr;
                    re[jp] = re[t] - lre;
                    im[jp] = im[t] - lim;
                    re[t] += lre;
                    im[t] += lim;
                    double nwre = wr * wRe - wi * wIm;
                    double nwim = wr * wIm + wi * wRe;
                    wr = nwre;
                    wi = nwim;
                }
            }
        }

        if (inverse) {
            for (int i = 0; i < n; i++) {
                re[i] /= n;
                im[i] /= n;
            }
        }
    }

    public static double[][] fft(int[] srcRe, int n, boolean inverse) {
        int m = srcRe.length;
        double[] dstRe = new double[n];
        double[] dstIm = new double[n];
        for (int i = 0; i < m; i++) {
            dstRe[i] = srcRe[i];
        }

        int h = Integer.numberOfTrailingZeros(n);
        for (int i = 0; i < n; i++) {
            int rev = Integer.reverse(i) >>> 32 - h;
            if (i < rev) {
                double d = dstRe[i];
                dstRe[i] = dstRe[rev];
                dstRe[rev] = d;
            }
        }

        for (int s = 2; s <= n; s <<= 1) {
            int nt = s >>> 1;
            double theta = inverse ? -2 * Math.PI / s : 2 * Math.PI / s;
            double wRe = Math.cos(theta);
            double wIm = Math.sin(theta);
            for (int j = 0; j < n; j += s) {
                double wr = 1, wi = 0;
                for (int t = j; t < j + nt; t++) {
                    int jp = t + nt;
                    double re = dstRe[jp] * wr - dstIm[jp] * wi;
                    double im = dstRe[jp] * wi + dstIm[jp] * wr;
                    dstRe[jp] = dstRe[t] - re;
                    dstIm[jp] = dstIm[t] - im;
                    dstRe[t] += re;
                    dstIm[t] += im;
                    double nwre = wr * wRe - wi * wIm;
                    double nwim = wr * wIm + wi * wRe;
                    wr = nwre;
                    wi = nwim;
                }
            }
        }

        if (inverse) {
            for (int i = 0; i < n; i++) {
                dstRe[i] /= n;
                dstIm[i] /= n;
            }
        }

        return new double[][]{dstRe, dstIm};
    }

    public static double[][][] convolute(int[][] a, int[][] b) {
        int m = Integer.highestOneBit(Math.max(1, Math.max(a.length, b.length) - 1)) << 2;
        int n = a.length;
        double[][][] fa = new double[m][][];
        for (int i = 0; i < n; i++) {
            fa[i] = fft(a[i], m, false);
        }
        for (int i = n; i < m; i++) {
            fa[i] = new double[2][m];
        }

        double[][][] tfa = new double[m][][];
        for (int i = 0; i < m; i++) {
            tfa[i] = new double[2][m];
            for (int j = 0; j < m; j++) {
                tfa[i][0][j] = fa[j][0][i];
                tfa[i][1][j] = fa[j][1][i];
            }
            fft(tfa[i][0], tfa[i][1], false);
        }

        double[][][] fb = new double[m][][];
        for (int i = 0; i < n; i++) {
            fb[i] = fft(b[i], m, false);
        }
        for (int i = n; i < m; i++) {
            fb[i] = new double[2][m];
        }

        double[][][] tfb = new double[m][][];
        for (int i = 0; i < m; i++) {
            tfb[i] = new double[2][m];
            for (int j = 0; j < m; j++) {
                tfb[i][0][j] = fb[j][0][i];
                tfb[i][1][j] = fb[j][1][i];
            }
            fft(tfb[i][0], tfb[i][1], false);
        }

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < m; i++) {
                double nfa0 = tfa[j][0][i] * tfb[j][0][i] - tfa[j][1][i] * tfb[j][1][i];
                double nfa1 = tfa[j][0][i] * tfb[j][1][i] + tfa[j][1][i] * tfb[j][0][i];
                tfa[j][0][i] = nfa0;
                tfa[j][1][i] = nfa1;
            }
            fft(tfa[j][0], tfa[j][1], true);
        }

        double[][][] r = new double[m][2][m];
        for (int j = 0; j < m; j++) {
            for (int k = 0; k < m; k++) {
                r[j][0][k] = tfa[k][0][j];
                r[j][1][k] = tfa[k][1][j];
            }
            fft(r[j][0], r[j][1], true);
        }

        return r;
    }

    public static void main(String[] args) throws Exception {
//		int n = 500, m = 99999;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(n + " ");
//		sb.append(n + " ");
//		for(int i = 0;i < n*n;i++){
//			sb.append(gen.nextInt(40)-20 + " ");
//		}
//		sb.append(n/2 + " ");
//		sb.append(n/2 + " ");
//		for(int i = 0;i < n*n/4;i++){
//			sb.append(gen.nextInt(40)-20 + " ");
//		}
//		INPUT = sb.toString();
//		int n = 500, m = 99999;
//		Random gen = new Random();
//		StringBuilder sb = new StringBuilder();
//		sb.append(n + " ");
//		sb.append(n + " ");
//		for(int i = 0;i < n*n;i++){
//			sb.append(gen.nextInt(40)-20 + " ");
//		}
//		sb.append(n + " ");
//		sb.append(n + " ");
//		for(int i = 0;i < n*n;i++){
//			sb.append(gen.nextInt(40)-20 + " ");
//		}
//		INPUT = sb.toString();

        long S = System.currentTimeMillis();
        is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
        out = new PrintWriter(System.out);

        solve();
        out.flush();
        long G = System.currentTimeMillis();
        tr(G - S + "ms");
    }

    private static boolean eof() {
        if (lenbuf == -1) return true;
        int lptr = ptrbuf;
        while (lptr < lenbuf) if (!isSpaceChar(inbuf[lptr++])) return false;

        try {
            is.mark(1000);
            while (true) {
                int b = is.read();
                if (b == -1) {
                    is.reset();
                    return true;
                } else if (!isSpaceChar(b)) {
                    is.reset();
                    return false;
                }
            }
        } catch (IOException e) {
            return true;
        }
    }

    private static byte[] inbuf = new byte[1024];
    static int lenbuf = 0, ptrbuf = 0;

    private static int readByte() {
        if (lenbuf == -1) throw new InputMismatchException();
        if (ptrbuf >= lenbuf) {
            ptrbuf = 0;
            try {
                lenbuf = is.read(inbuf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (lenbuf <= 0) return -1;
        }
        return inbuf[ptrbuf++];
    }

    private static boolean isSpaceChar(int c) {
        return !(c >= 33 && c <= 126);
    }

    private static int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    private static double nd() {
        return Double.parseDouble(ns());
    }

    private static char nc() {
        return (char) skip();
    }

    private static String ns() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    private static char[] ns(int n) {
        char[] buf = new char[n];
        int b = skip(), p = 0;
        while (p < n && !(isSpaceChar(b))) {
            buf[p++] = (char) b;
            b = readByte();
        }
        return n == p ? buf : Arrays.copyOf(buf, p);
    }

    private static char[][] nm(int n, int m) {
        char[][] map = new char[n][];
        for (int i = 0; i < n; i++) map[i] = ns(m);
        return map;
    }

    private static int[] na(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = ni();
        return a;
    }

    private static int ni() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static long nl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if (b == '-') {
            minus = true;
            b = readByte();
        }

        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    private static void tr(Object... o) {
        if (INPUT.length() != 0) System.out.println(Arrays.deepToString(o));
    }
}