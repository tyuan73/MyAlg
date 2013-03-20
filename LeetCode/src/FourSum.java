import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FourSum {
    class Element implements Comparable<Element> {
        int x1, x2;
        int sum;

        Element(int a1, int a2) {
            this.x1 = a1;
            this.x2 = a2;
            this.sum = this.x1 + this.x2;
        }

        public int compareTo(Element e) {
            return this.sum - e.sum;
        }
    }

    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        if (num == null || num.length == 0)
            return ret;

        Arrays.sort(num);
        int n = num.length;
        List<Element> list = new ArrayList<Element>();
        for (int i = 0; i < n - 1; i = nextI(num, i, true)) {
            for (int j = i + 1; j < n; j = nextI(num, j, true)) {
                list.add(new Element(num[i], num[j]));
            }
        }

        Collections.sort(list);

        n = list.size();
        int l = 0, r = n - 1;
        while (l < r) {
            Element e1 = list.get(l), e2 = list.get(r);
            int sum = e1.sum + e2.sum;
            if (sum == target) {
                int[] x = new int[4];
                x[0] = e1.x1;
                x[1] = e1.x2;
                x[2] = e2.x1;
                x[3] = e2.x2;
                Arrays.sort(x);
                ArrayList<Integer> e = new ArrayList<Integer>();
                for (int y : x)
                    e.add(y);
                ret.add(e);
                l++;
                r--;
            } else if (sum > target)
                l++;
            else
                r--;
        }

        return ret;
    }

    int nextI(int[] num, int i, boolean up) {
        int step = up ? 1 : -1;
        i += step;
        while (i >= 0 && i < num.length) {
            if (num[i] != num[i - step])
                break;
            i += step;
        }
        return i;
    }
}