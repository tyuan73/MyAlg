/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/29/13
 * Time: 12:31 AM
 * To change this template use File | Settings | File Templates.
 *
 *                          C. Anagram
 * http://www.codeforces.com/problemset/problem/254/C
 *
 */

import java.io.*;
public class MinAnagramTransfer {
    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
        String S = f.readLine();
        String T = f.readLine();
        f.close();

        int[] count1 = new int[26];
        int[] count2 = new int[26];
        for(int i = 0; i < T.length(); i++)
            count1[T.charAt(i) - 'A']++;
        for(int i = 0; i < S.length(); i++)
            count2[S.charAt(i) - 'A']++;

        StringBuffer sb = new StringBuffer();
        int start = 0;
        int step = 0;
        for(int i = 0; i < S.length(); i++) {
            int index = S.charAt(i)-'A';
            if(count1[index] >= count2[index]) {
                sb.append(S.charAt(i));
                count1[index]--;
                count2[index]--;
            }
            else {
                start = 0;
                while(count1[start] <= count2[start] && (start != index || count1[start] == 0)) {
                    start++;
                }
                count1[start]--;
                count2[index]--;
                sb.append((char) (start + 'A'));
                if(start != index) step++;
            }
        }
        out.println(step);
        out.println(sb.toString());

        out.close();
    }
}
