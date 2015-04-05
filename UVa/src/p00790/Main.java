package p00790;/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 4/5/15
 * Time: 12:04 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    static class Submit implements Comparable<Submit> {
        int time, team, pro;
        boolean solved;

        Submit(int time, int team, char p, char res) {
            this.time = time;
            this.team = team;
            this.pro = p - 'A';
            this.solved = res == 'Y';
        }

        public int compareTo(Submit s) {
            if (this.time == s.time) {
                return this.solved ? 1 : -1;
            }
            return this.time - s.time;
        }
    }

    static class Team implements Comparable<Team> {
        int id, solved, time;

        Team(int id, int solved, int time) {
            this.id = id;
            this.solved = solved;
            this.time = time;
        }

        public int compareTo(Team t) {
            if (this.solved == t.solved) {
                if (this.time == t.time) {
                    return this.id - t.id;
                }
                return this.time - t.time;
            }

            return t.solved - this.solved;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int T = Integer.parseInt(in.nextLine());
        in.nextLine();

        while(T-- > 0) {

            List<Submit> list = new ArrayList<Submit>();
            int maxT = 0;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.length() == 0) break;
                String[] cols = line.split(" ");
                String[] times = cols[2].split(":");
                int time = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
                int team = Integer.parseInt(cols[0]);
                list.add(new Submit(time, team, cols[1].charAt(0), cols[3].charAt(0)));
                maxT = Math.max(team, maxT);
            }

            Collections.sort(list);

            int[][] result = new int[maxT + 1][7];
            for (Submit s : list) {
                if (result[s.team][s.pro] <= 0) {
                    if (s.solved) {
                        result[s.team][s.pro] = s.time - result[s.team][s.pro] * 20;
                    } else {
                        result[s.team][s.pro]--;
                    }
                }
            }

            List<Team> tlist = new ArrayList<>();
            for (int i = 1; i <= maxT; i++) {
                int solved = 0, time = 0;
                for (int x : result[i]) {
                    if (x > 0) {
                        solved++;
                        time += x;
                    }
                }
                tlist.add(new Team(i, solved, time));
            }

            Collections.sort(tlist);

            System.out.println("RANK TEAM PRO/SOLVED TIME");
            int rank = 1;
            print(1, tlist.get(0));
            for (int i = 1; i < tlist.size(); i++) {
                Team t = tlist.get(i);
                Team tp = tlist.get(i - 1);
                if (t.solved < tp.solved || t.time > tp.time) {
                    print(i + 1, t);
                    rank = i + 1;
                    continue;
                }
                print(rank, t);
            }

            System.out.println();
        }
    }

    static void print(int rank, Team t) {
        if (t.solved > 0)
            System.out.printf("%4d%5d%5d%11d\n", rank, t.id, t.solved, t.time);
        else
            System.out.printf("%4d%5d\n", rank, t.id);

    }
}
