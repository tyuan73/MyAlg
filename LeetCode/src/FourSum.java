import java.util.ArrayList;
import java.util.Arrays;

public class FourSum {
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();

        int n = num.length;
        if (n == 0)
            return ret;

        Arrays.sort(num);
        int pre1 = num[0] - 1;             // pre1 must be inited to a number that is not in the array, pre2, pre3, pre4 too.
        for (int i = 0; i < n - 3; i++) {
            if (num[i] == pre1)
                continue;

            int pre2 = num[0] - 1;
            for (int j = i + 1; j < n - 2; j++) {
                if (num[j] == pre2)
                    continue;

                int l = j + 1, r = n - 1;
                int pre3 = num[0] - 1, pre4 = num[0] - 1;
                while (l < r) {
                    if (num[l] == pre3) {
                        l++;
                        continue;
                    }
                    if (num[r] == pre4) {
                        r--;
                        continue;
                    }

                    int sum = num[i] + num[j] + num[l] + num[r];
                    if (sum == target) {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(num[i]);
                        list.add(num[j]);
                        list.add(num[l]);
                        list.add(num[r]);
                        ret.add(list);
                        pre3 = num[l];
                        pre4 = num[r];
                        l++;
                        r--;
                    } else if (sum < target) {
                        pre3 = num[l];
                        l++;
                    } else {
                        pre4 = num[r];
                        r--;
                    }
                }

                pre2 = num[j];
            }

            pre1 = num[i];
        }

        return ret;
    }
}