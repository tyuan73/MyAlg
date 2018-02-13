import java.util.*;
import java.io.*;

public class RabbitsInForest781 {

    public int numRabbits(int[] a) {
        Arrays.sort(a);
        int total = 0, r = 0;
        for (int i = 0; i < a.length; i++) {
            if (r-- == 0 || a[i] != a[i - 1]) {
                r = a[i];
                total += a[i] + 1;
            }
        }
        return total;
    }

    public int numRabbits1(int[] a) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i : a)
            m.put(i, m.getOrDefault(i, 0) + 1);
        int res = 0;
        for (int i : m.keySet())
            res += (m.get(i) + i) / (i + 1) * (i + 1);
        return res;
    }
}
