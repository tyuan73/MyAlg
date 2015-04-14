package p10226;

/**
 * Created by yuantian on 4/14/15.
 */

import java.util.*;

class Main1 {
    static class Tree implements Comparable<Tree> {
        String name;
        int pop;

        Tree(String str, int p) {
            this.name = str;
            this.pop = p;
        }

        public int compareTo(Tree t) {
            return this.name.compareTo(t.name);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = Integer.parseInt(in.nextLine());
        in.nextLine();
        HashMap<String, Integer> map = new HashMap<>();
        int total = 0;
        while (true) {
            String line = null;
            if (!in.hasNextLine() || (line = in.nextLine()).length() == 0) {
                ArrayList<Tree> trees = new ArrayList<>();
                for (String s : map.keySet())
                    trees.add(new Tree(s, map.get(s)));
                Collections.sort(trees);
                for (Tree tree : trees) {
                    System.out.printf("%s %.4f\n", tree.name, tree.pop / (double)total * 100);
                }

                if (t != 1)
                    System.out.println();
                map.clear();
                total = 0;
                t--;
                if (in.hasNextLine())
                    continue;
                else
                    break;
            }

            total += 1;
            int p = 1;
            if (map.containsKey(line)) {
                p = map.get(line) + 1;
            }
            map.put(line, p);
        }
    }
}
