package hack101.aug2014;

/**
 * Created by yuantian on 9/2/14.
 */

/*

*/

import java.util.*;
import java.io.*;

public class Pangrams {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        boolean[] map = new boolean[26];
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (a == ' ')
                continue;
            if (a >= 'A' && a <= 'Z')
                a -= 'A';
            else
                a -= 'a';
            if(!map[a]) {
                count++;
                map[a] = true;
            }
            if (count == 26)
                break;
        }
        if (count == 26)
            System.out.println("pangram");
        else
            System.out.println("not pangram");
    }
}