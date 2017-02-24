/**
 * Created by yuantian on 2/24/17.
 */

import java.util.*;

public class IntegerReplacement397 {
    /**
     * BFS
     */
    public int integerReplacement(int n) {
        long m = n;
        Set<Long> visited = new HashSet<>();
        List<Long> next = new ArrayList<>();
        next.add(m);
        visited.add(m);
        int ans = 0;
        List<Long> temp = new ArrayList<>();
        while (!next.isEmpty()) {
            temp.clear();
            for (long x : next) {
                if (x == 1) return ans;
                if ((x & 1) == 1) {
                    visit(visited, temp, x - 1);
                    visit(visited, temp, x + 1);
                } else {
                    visit(visited, temp, x / 2);
                }
            }
            ans++;
            List<Long> l = next;
            next = temp;
            temp = l;
        }
        return 0;
    }

    void visit(Set<Long> visited, List<Long> list, long y) {
        if (!visited.contains(y)) {
            visited.add(y);
            list.add(y);
        }
    }

    /**
     * A much better solution - a greedy solution?
     */
    public int integerReplacement1(int n) {
        if (n == Integer.MAX_VALUE)
            return 32; //n = 2^31-1;
        int count = 0;
        while (n > 1) {
            if ((n & 1) == 0) // if n is even, easy
                n >>>= 1;
            else {
                // if n is odd and the last 2 bits of n is 11 - note: 3 is an exception
                if ((n & 3) == 3 && (n != 3))
                    n++;
                else
                    n--;
            }
            count++;
        }
        return count;
    }
}
