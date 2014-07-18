package weekly.week7.day5;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by yuantian on 7/18/14.
 */
public class Test {

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter writer = new PrintWriter("/tmp/test.txt");

        int n = 5;
        int q = 10;
        writer.println(n + " " + q);
        Random r = new Random();
        while(q-- > 0) {
            int op = r.nextInt(10) + 1;
            switch (op) {
                case 10:
                case 9:
                case 8:
                case 7:
                case 6:
                case 5:
                case 4:
                case 1:
                    int l1 = r.nextInt(n) + 1;
                    int l2 = r.nextInt(n) + 1;
                    if (l1 > l2)
                        writer.println("1 " + l2 + " " + l1);
                    else
                        writer.println("1 " + l1 + " " + l2);
                    break;
                case 2:
                    int l = r.nextInt(n) + 1;
                    writer.println(op + " " + l);
                    break;
                case 3:
                    l = r.nextInt(n) + 1;
                    writer.println(op + " " + l);
                    break;
            }
        }
        writer.close();
    }
}
