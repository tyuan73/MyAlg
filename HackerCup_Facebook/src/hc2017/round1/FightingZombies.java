package hc2017.round1;

import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

/**
 * Created by yuantian on 1/25/17.
 */
public class FightingZombies {
    FastScanner in;
    PrintWriter out;

    TreeSet<Zombie> set = new TreeSet<>(new Comparator<Zombie>() {
        @Override
        public int compare(Zombie o1, Zombie o2) {
            return o1.y - o2.y;
        }
    });

    class Zombie {
        int x, y, id;

        public Zombie(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }

    public void solve() throws IOException {
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int n = in.nextInt();
            int r = in.nextInt();
            //int[][] z = new int[n][2];
            Zombie[] z = new Zombie[n];
            for (int i = 0; i < n; i++) {
                int x = in.nextInt(), y = in.nextInt();
                z[i] = new Zombie(x, y, i);
            }
            Arrays.sort(z, new Comparator<Zombie>() {
                @Override
                public int compare(Zombie o1, Zombie o2) {
                    return o1.x - o2.x;
                }
            });

            set.clear();

            System.out.printf("Case #%d: %d\n", t, oneCase(n, r, z));
        }
    }

    int oneCase(int n, int r, Zombie[] z) {
        List<Integer>[] count = new List[n];
        for (List<Integer> e : count)
            e = new ArrayList<>();

        int[] grpCount = new int[n];
        int grpId = 0;

        int l = 0, u = 0;
        while (l < n) {
            int low = z[l].x;
            while (u < n && z[u].x <= z[l].x + r) {
                set.add(z[u++]);
            }

            // scan y values
            int left = 0, right = 0;
            Zombie cur = set.first();
            if (cur != null) {
                left = cur.y;
                right = left;
            }
            while (cur != null) {
                //Zombie next =
                grpCount[grpId]++;
                count[cur.id].add(grpId);

                grpId++;

            }

            // find next frame
            while (l < u && z[l].x <= z[u].x - r) l++;
        }
        return 1;
    }

    public void run() {
        try {
            in = new FastScanner(new File("/home/yuantian/IdeaProjects/MyAlg/HackerCup_Facebook/src/hc2017/round1/FightingZombies_small.in"));
            out = new PrintWriter(new File("/tmp/test/FightingZombies.out"));

            solve();

            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] arg) {
        new FightingZombies().run();
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(File file) throws IOException {
            br = new BufferedReader(new FileReader(file));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                    return "";
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
