/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 2/28/13
 * Time: 12:24 AM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class LittleGirlGame {
    static public void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        int[] count = new int[26];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'a']++;
        }

        int odd = 0;
        for (int i : count)
            if ((i % 2) == 1)
                odd++;

        if (odd == 0 || (odd % 2) == 1) {
            System.out.println("First");
        } else
            System.out.println("Second");
    }
}
