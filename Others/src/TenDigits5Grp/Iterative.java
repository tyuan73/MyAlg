package TenDigits5Grp;

/**
 * Created by yuantian on 9/4/15.
 */
public class Iterative {
    final static int MAX = (1 << 10) - 1;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] bits = new int[1089];
        for (int i = 1; i < 1089; i++) {
            bits[i] = getBits(i);
        }
        bits[0] = 1;

        int a, b, c, d, e;
        int mask = 0, count = 0;
        StringBuilder sb = new StringBuilder();
        for (a = 1; a < 10; a++) {
            mask |= bits[a];
            int r1 = 1089 - a;
            for (b = a + 1; b < 99; b++) {
                if (bits[b] == 0 || ((bits[b] | mask) != bits[b] + mask)) continue;

                int r2 = r1 - b;
                mask |= bits[b];

                for (c = b + 1; c <= Math.min(99, r2 / 3); c++) {
                    if (bits[c] == 0 || ((bits[c] | mask) != bits[c] + mask)) continue;

                    int r3 = r2 - c;
                    mask |= bits[c];

                    int last = MAX ^ mask;
                    for (d = c + 1, e = r3 - d; d <= r3 / 2; e = r3 - ++d) {
                        if (bits[e] == 0 || bits[d] == 0 || (bits[e] & bits[d]) != 0 || last != (bits[d] | bits[e]))
                            continue;

                        sb.append(a).append(" + ");
                        sb.append(b).append(" + ");
                        sb.append(c).append(" + ");
                        sb.append(d).append(" + ");
                        sb.append(e).append(" = 1089\n");
                        count++;
                    }

                    mask ^= bits[c];
                }

                mask ^= bits[b];
            }

            mask ^= bits[a];
        }
        System.out.println(sb);
        System.out.println("count = " + count);
        System.out.println("Total time = " + (System.currentTimeMillis() - start));
    }

    static int getBits(int n) {
        int bits = 0;
        while (n > 0) {
            int d = n % 10;
            if ((bits & (1 << d)) > 0) {
                return 0;
            }
            bits |= 1 << d;
            n /= 10;
        }
        return bits;
    }
}
