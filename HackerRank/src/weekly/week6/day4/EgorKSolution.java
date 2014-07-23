package weekly.week6.day4;

/**
 * EgorK's solution
 * https://www.hackerrank.com/rest/contests/w6/challenges/best-spot/hackers/uwi/download_solution
 */

import java.io.BufferedWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.math.BigInteger;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.io.IOException;
import java.util.Arrays;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Comparator;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Egor Kulikov (egor@egork.net)
 */
public class EgorKSolution {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        BestSpotEgorK solver = new BestSpotEgorK();
        solver.solve(1, in, out);
        out.close();
    }
}

class BestSpotEgorK {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int height = in.readInt();
        int width = in.readInt();
        int[][] grid = IOUtils.readIntTable(in, height, width);
        int rowCount = in.readInt();
        int columnCount = in.readInt();
        int[][] sample = IOUtils.readIntTable(in, rowCount, columnCount);
        long[] first = new long[height * width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                first[i * width + j] = grid[i][j];
                grid[i][j] *= grid[i][j];
            }
        }
        long[] second = new long[rowCount * width];
        long sampleSquared = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                second[second.length - 1 - i * width - j] = sample[i][j];
                sampleSquared += sample[i][j] * sample[i][j];
            }
        }

        printArray(first, out);
        printArray(second, out);
        long[] test1 = testMultiply(first, second);
        printArray(test1, out);

        long[] result = FastFourierTransform.multiply(first, second);

        printArray(result, out);

        long[][] gridSquared = ArrayUtils.partialSums(grid);
        long best = Long.MAX_VALUE;
        int row = -1;
        int column = -1;
        for (int i = 0; i <= height - rowCount; i++) {
            for (int j = 0; j <= width - columnCount; j++) {
                long candidate = gridSquared[i][j] + gridSquared[i + rowCount][j + columnCount] -
                        gridSquared[i][j + columnCount] - gridSquared[i + rowCount][j] + sampleSquared -
                        2 * result[i * width + j + second.length - 1];
                out.printLine("result[" + (i * width + j + second.length - 1) + "]:" + result[i * width + j + second.length - 1]);
                if (candidate < best) {
                    best = candidate;
                    row = i + 1;
                    column = j + 1;
                }
            }
        }
        out.printLine(best);
        out.printLine(row, column);
    }

/*
Input:
3 3
19 19 -12
5 8 -14
-12 -11 9
2 2
-18 -12
-10 -7

Array first before multiplication:
 19 19 -12 5 8 -14 -12 -11 9
Array second before multiplication:
 0 -7 -10 0 -12 -18

Result:
 0 -133 -323 -106 -143 -676 -180 380 11 71 306 348 90 -162 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
result[5]:-676
result[6]:-180
result[8]:11
result[9]:71

output:
937
2 2
*/
    static long[] testMultiply(long[] a, long[] b) {
        long[] ret = new long[a.length + b.length - 1];
        int idx = 0;
        int enda = 0, startb = 1;
        while(enda < a.length) {
            for(int i = enda, j = 0; i >= 0 && j < b.length; i--, j++)
                ret[idx] += a[i] * b[j];
            enda++;
            idx++;
        }
        while(startb < b.length) {
            for(int i = a.length-1, j = startb; i >= 0 && j < b.length; i--, j++)
                ret[idx] += a[i] * b[j];
            startb++;
            idx++;
        }
        return ret;
    }

    static void printArray(long[] a, OutputWriter out) {
        for(long x : a)
            out.print(" " + x);
        out.print("\n");
    }
    static void printArray(long[][] a, OutputWriter out) {
        for(long[] l : a) {
            for(long x : l)
                out.print(" " + x);
            out.print("\n");
        }
    }
}

class FastFourierTransform {
    public static void fft(double[] a, double[] b, boolean invert) {
        int count = a.length;
        for (int i = 1, j = 0; i < count; i++) {
            int bit = count >> 1;
            for (; j >= bit; bit >>= 1)
                j -= bit;
            j += bit;
            if (i < j) {
                double temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                temp = b[i];
                b[i] = b[j];
                b[j] = temp;
            }
        }
        for (int len = 2; len <= count; len <<= 1) {
            int halfLen = len >> 1;
            double angle = 2 * Math.PI / len;
            if (invert)
                angle = -angle;
            double wLenA = Math.cos(angle);
            double wLenB = Math.sin(angle);
            for (int i = 0; i < count; i += len) {
                double wA = 1;
                double wB = 0;
                for (int j = 0; j < halfLen; j++) {
                    double uA = a[i + j];
                    double uB = b[i + j];
                    double vA = a[i + j + halfLen] * wA - b[i + j + halfLen] * wB;
                    double vB = a[i + j + halfLen] * wB + b[i + j + halfLen] * wA;
                    a[i + j] = uA + vA;
                    b[i + j] = uB + vB;
                    a[i + j + halfLen] = uA - vA;
                    b[i + j + halfLen] = uB - vB;
                    double nextWA = wA * wLenA - wB * wLenB;
                    wB = wA * wLenB + wB * wLenA;
                    wA = nextWA;
                }
            }
        }
        if (invert) {
            for (int i = 0; i < count; i++) {
                a[i] /= count;
                b[i] /= count;
            }
        }
    }

    public static long[] multiply(long[] a, long[] b) {
        int resultSize = Integer.highestOneBit(Math.max(a.length, b.length) - 1) << 2;
        resultSize = Math.max(resultSize, 1);
        double[] aReal = new double[resultSize];
        double[] aImaginary = new double[resultSize];
        double[] bReal = new double[resultSize];
        double[] bImaginary = new double[resultSize];
        for (int i = 0; i < a.length; i++)
            aReal[i] = a[i];
        for (int i = 0; i < b.length; i++)
            bReal[i] = b[i];
        fft(aReal, aImaginary, false);
        if (a == b) {
            System.arraycopy(aReal, 0, bReal, 0, aReal.length);
            System.arraycopy(aImaginary, 0, bImaginary, 0, aImaginary.length);
        } else
            fft(bReal, bImaginary, false);
        for (int i = 0; i < resultSize; i++) {
            double real = aReal[i] * bReal[i] - aImaginary[i] * bImaginary[i];
            aImaginary[i] = aImaginary[i] * bReal[i] + bImaginary[i] * aReal[i];
            aReal[i] = real;
        }
        fft(aReal, aImaginary, true);
        long[] result = new long[resultSize];
        for (int i = 0; i < resultSize; i++)
            result[i] = Math.round(aReal[i]);
        return result;
    }
}

class ArrayUtils {

    public static long[][] partialSums(int[][] array) {
        int height = array.length;
        int width = array[0].length;
        long[][] result = new long[height + 1][width + 1];
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                result[i][j] = result[i][j - 1] + result[i - 1][j] - result[i - 1][j - 1] + array[i - 1][j - 1];
            }
        }
        return result;
    }

}


class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int readInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public boolean isSpaceChar(int c) {
        if (filter != null)
            return filter.isSpaceChar(c);
        return isWhitespace(c);
    }

    public static boolean isWhitespace(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public interface SpaceCharFilter {
        public boolean isSpaceChar(int ch);
    }
}

class OutputWriter {
    private final PrintWriter writer;

    public OutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
    }

    public OutputWriter(Writer writer) {
        this.writer = new PrintWriter(writer);
    }

    public void print(Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            if (i != 0)
                writer.print(' ');
            writer.print(objects[i]);
        }
    }

    public void printLine(Object... objects) {
        print(objects);
        writer.println();
    }

    public void close() {
        writer.close();
    }

    public void printLine(long i) {
        writer.println(i);
    }

}

class IOUtils {

    public static int[] readIntArray(InputReader in, int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = in.readInt();
        return array;
    }

    public static int[][] readIntTable(InputReader in, int rowCount, int columnCount) {
        int[][] table = new int[rowCount][];
        for (int i = 0; i < rowCount; i++)
            table[i] = readIntArray(in, columnCount);
        return table;
    }

}