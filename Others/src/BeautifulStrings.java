/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/25/13
 * Time: 11:25 PM
 * To change this template use File | Settings | File Templates.
 */
import java.util.*;

public class BeautifulStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] input = new String[n];
        for(int i = 0; i < n; i++) {
            input[i] = in.nextLine();
        }

        for(int i = 0; i < n; i++) {
            System.out.printf("Case #%d: %d\n", i+1, getBeauty(input[i]));
        }

        in.close();
    }

    static int getBeauty(String str) {
        int[] count = new int[26];
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch >= 'A' && ch <= 'Z')
                ch -= 'A' - 'a';
            if(ch >= 'a' && ch <= 'z')
                count[ch-'a']++;
        }

        Arrays.sort(count);
        int ret = 0;
        for(int i = 25; i >= 0; i--) {
            ret += count[i] * (i+1);
        }
        return ret;
    }
}
