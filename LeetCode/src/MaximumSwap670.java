public class MaximumSwap670 {
    public int maximumSwap(int num) {
        char[] str = Integer.toString(num).toCharArray();
        int n = str.length;
        int[] dig = new int[n];
        int idx1 = n - 1, idx2 = n - 1;
        for (int i = n - 1, v = 1, max = n - 1; i >= 0; i--, v *= 10) {
            dig[i] = v;
            if (str[i] > str[max])
                max = i;
            else if (str[i] < str[max]) {
                idx1 = i;
                idx2 = max;
            }
        }
        return num + (str[idx2] - str[idx1]) * (dig[idx1] - dig[idx2]);
    }
}
