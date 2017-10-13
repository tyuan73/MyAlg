package zepto2014.problemA;

/**
 * Created by yuantian on 6/13/14.
 */

import java.util.*;

public class FeedWithCandy {
    final static int P = 4000;

    static class Candy implements Comparable<Candy> {
        int height, weight;
        Candy(int h, int w) {
            this.height = h;
            this.weight = w;
        }

        public int compareTo(Candy c) {
            return this.height - c.height;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int x = in.nextInt();

        PriorityQueue<Candy> p = new PriorityQueue<Candy>();
        PriorityQueue<Candy> q = new PriorityQueue<Candy>();
        PriorityQueue<Integer> pw = new PriorityQueue<Integer>();
        PriorityQueue<Integer> qw = new PriorityQueue<Integer>();

        for(int i = 0; i < n; i++) {
            int t = in.nextInt();
            int h = in.nextInt();
            int m = in.nextInt();
            if (t == 0) {
                if (h > x) {
                    p.add(new Candy(h, m));
                } else {
                    pw.add(P - m);
                }
            } else {
                if (h > x) {
                    q.add(new Candy(h, m));
                } else {
                    qw.add(P - m);
                }
            }
        }

        PriorityQueue<Candy> p1 = new PriorityQueue<Candy>(p);
        PriorityQueue<Candy> q1 = new PriorityQueue<Candy>(q);
        PriorityQueue<Integer> pw1 = new PriorityQueue<Integer>(pw);
        PriorityQueue<Integer> qw1 = new PriorityQueue<Integer>(qw);
        int best0 = check(x, pw1, qw1, p1, q1);

        int best1 = check(x, qw, pw, q, p);

        System.out.println(Math.max(best0, best1));
    }

    static int check(int myx, PriorityQueue<Integer> pw,
                     PriorityQueue<Integer> qw, PriorityQueue<Candy> p, PriorityQueue<Candy> q) {
        int best = 0;
        while(pw.size() > 0) {
            myx += (P - pw.poll());
            best++;
            while(q.size() > 0) {
                if (myx >= q.peek().height) {
                    qw.add(P - q.poll().weight);
                } else {
                    break;
                }
            }
            if (qw.size() > 0) {
                myx += (P - qw.poll());
                best++;
            } else {
                break;
            }

            while(p.size() > 0) {
                if (myx >= p.peek().height) {
                    pw.add(P - p.poll().weight);
                } else {
                    break;
                }
            }
        }
        return best;
    }
}

/*
import java.util.*;

public class TheArtfulExpedient869A {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int x = in.nextInt();

    int[][] TheIntriguingObsession869C = new int[n][3];
    for(int i=0; i<n; i++) {
      TheIntriguingObsession869C[i][0] = in.nextInt(); //type
      TheIntriguingObsession869C[i][1] = in.nextInt(); //height
      TheIntriguingObsession869C[i][2] = in.nextInt(); //+height
    }

    int best = 0;
    for(int st=0; st<=1; st++) {
      int score = 0;
      int prev = st;
      int my_x = x;
      boolean[] USED = new boolean[n];
      while(true) {
        int best_i = -1;
        for(int i=0; i<n; i++) {
          if(USED[i] || TheIntriguingObsession869C[i][1]>my_x || TheIntriguingObsession869C[i][0]==prev) { continue; }
          if(best_i == -1 || (TheIntriguingObsession869C[i][2] > TheIntriguingObsession869C[best_i][2])) {
            best_i = i;
          }
        }
        if(best_i == -1) { break; }

        score++;
        my_x += TheIntriguingObsession869C[best_i][2];
        prev = TheIntriguingObsession869C[best_i][0];
        USED[best_i] = true;
      }
      best = Math.max(best, score);
    }
    System.out.println(best);
  }
}
*/

/*
Test case:
8 2
0 20 3
1 5 2
1 4 1
1 7 1
0 1 3
1 5 3
1 7 2
1 3 1

output:
2

Test case:
2 1
1 1 1
0 2 1

output:
2
 */