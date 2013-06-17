/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 3/4/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

public class LittleElephantBits {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.next();

        int index = input.indexOf("0");
        String output = null;
        if (index < 0)
            output = input.substring(1);
        else
            output = input.substring(0, index) + input.substring(index + 1);

        if (output.length() == 0) {
            System.out.println(0);
        } else {
            System.out.println(output);
        }
    }
}
