/*
ID: tyuan731
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.*;

class namenum {
    static char[][] map = {{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}, {'J', 'K', 'L'},
            {'M', 'N', 'O'}, {'P', 'R', 'S'}, {'T', 'U', 'V'}, {'W', 'X', 'Y'}};
    static String[] dict = new String[5000];
    static int count = 0;

    static void readDict() throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("dict.txt"));
        String s = null;
        while ((s = f.readLine()) != null) {
            dict[count++] = s;
        }
        f.close();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
        readDict();

        String number = f.readLine();
        ArrayList<String> ret = new ArrayList<String>();
        find(number, 0, 0, count - 1, ret);
        if (ret.size() == 0)
            out.println("NONE");
        else {
            for (String s : ret)
                out.println(s);
        }

        out.close();                                  // close the output file
        System.exit(0);                               // don't omit this!
    }

    static void find(String number, int idx, int from, int to, ArrayList<String> ret) {
        if (idx == number.length()) {
            if (dict[from].length() == idx)
                ret.add(dict[from]);
            return;
        }
        if (from > to) {
            return;
        }

        int mapidx = number.charAt(idx) - '2';
        int i1 = from;
        for (int i = 0; i <= 2; i++) {
            char ch = map[mapidx][i];
            for (; i1 <= to; i1++)
                if (dict[i1].length() > idx) {
                    if (ch <= dict[i1].charAt(idx))
                        break;
                }
            int j1 = Math.min(i1, to);
            if (dict[j1].length() <= idx || ch != dict[j1].charAt(idx))
                continue;

            while (i1 <= to && ch == dict[i1].charAt(idx))
                i1++;
            i1--;

            find(number, idx + 1, j1, i1, ret);
        }
    }
}