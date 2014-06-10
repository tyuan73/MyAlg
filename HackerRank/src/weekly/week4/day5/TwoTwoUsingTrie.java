package weekly.week4.day5;

/**
 * Created by yuantian on 6/9/14.
 */
import java.math.BigInteger;
import java.util.*;

public class TwoTwoUsingTrie {
    static class Trie {
        Trie[] children;
        boolean isPOT; // is power of two

        Trie() {
            children = new Trie[10];
            isPOT = false;
        }

        void insert(String s) {
            Trie cur = this;
            for(int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - '0';
                if (cur.children[idx] == null)
                    cur.children[idx] = new Trie();
                cur = cur.children[idx];
            }
            cur.isPOT = true;
        }

        int count(String s) {
            int res = 0;
            Trie cur = this;
            for(int i = 0; i < s.length(); i++) {
                int idx = s.charAt(i) - '0';
                if (cur.children[idx] == null)
                    return res;
                cur = cur.children[idx];
                if (cur.isPOT)
                    res++;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Trie root = new Trie();
        BigInteger power = BigInteger.ONE;
        for(int i = 0; i <= 800; i++) {
            root.insert(power.toString());
            power = power.multiply(BigInteger.valueOf(2));
        }

        int t = in.nextInt();
        in.nextLine();
        while(t-- > 0) {
            String line = in.nextLine();
            int output = 0;
            for(int i = 0; i < line.length(); i++) {
                output += root.count(line.substring(i));
            }
            System.out.println(output);
        }
    }
}
