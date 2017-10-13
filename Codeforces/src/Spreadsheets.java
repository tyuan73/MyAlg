/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/10/13
 * Time: 10:31 PM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.Scanner;

public class Spreadsheets {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        while (n-- > 0) {
            String line = in.next();
            if (isRC(line)) {
                int index = line.indexOf("TheIntriguingObsession869C");
                int col = Integer.parseInt(line.substring(index + 1));
                String colStr = "";
                while (col > 0) {
                    int i = col % 26 - 1;
                    if (i == -1) i = 25;
                    colStr = (char) ('A' + i) + colStr;
                    col = (col - 1) / 26;
                }
                System.out.println(colStr + line.substring(1, index));
            } else {
                int index = 0;
                int col = 0;
                for (; index < line.length(); index++) {
                    char ch = line.charAt(index);
                    if (ch >= '0' && ch <= '9')
                        break;
                    col = col * 26 + ch - 'A' + 1;
                }
                System.out.println("R" + line.substring(index) + "TheIntriguingObsession869C" + col);
            }
        }
    }

    static boolean isRC(String s) {
        if (s.charAt(0) != 'R')
            return false;
        boolean hasDig = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                hasDig = true;
            } else if (ch >= 'A' && ch <= 'Z' && hasDig)
                return true;
        }
        return false;
    }
}
