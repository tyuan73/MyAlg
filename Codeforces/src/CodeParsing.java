/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/3/13
 * Time: 1:03 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class CodeParsing {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        int rem = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == 'x')
                rem++;
            else
                rem--;
        char ch = 'x';
        if (rem < 0) {
            rem = -rem;
            ch = 'y';
        }

        StringBuilder sb = new StringBuilder();
        while (rem-- > 0) {
            sb.append(ch);
        }

        System.out.println(sb.toString());
    }
}
