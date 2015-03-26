class PermutateArrayLexi {

    public static void main(String[] args) {
        PermutateArrayLexi pa = new PermutateArrayLexi();

        pa.getPermutation(4);

    }

    public void getPermutation(int n) {

        int[] ans = new int[n];
        for (int i = 0; i < n; i++)
            ans[i] = i + 1;

        perm(ans, 0);

    }

    void perm(int[] arr, int from) {
        if (from == arr.length) {
            getStr(arr);
            return;
        }

        for (int i = from; i < arr.length; i++) {
            swap1(arr, from, i);
            perm(arr, from + 1);
            swap2(arr, from, i);
        }
    }

    void swap1(int[] arr, int i, int j) {
        int x = arr[j];
        for (int k = j; k > i; k--)
            arr[k] = arr[k - 1];
        arr[i] = x;
    }

    void swap2(int[] arr, int i, int j) {
        int x = arr[i];
        for (int k = i; k < j; k++)
            arr[k] = arr[k + 1];
        arr[j] = x;
    }

    public String getPermutation1(int n, int k) {
        int[] table = new int[10];
        table[1] = 1;
        for (int i = 2; i <= 9; i++)
            table[i] = table[i - 1] * i;

        int[] ans = new int[n];
        for (int i = 0; i < n; i++)
            ans[i] = i + 1;

        int index = n;
        while (k > 0) {
            //System.out.println("k = " + k  + " index = " + index);

            if (table[index] > k) {
                index--;
                continue;
            }
            //index = find(table, k);
            int dis = (k - 1) / table[index];
            System.out.println("k = " + k + "num =" + table[index] + " index = " + index + " dist =" + dis);
            k %= table[index];
            if (dis > 0)
                swap(ans, n - index - 1, n - index - 1 + dis);
        }
        reverse(ans, n - index, n - 1);

        return getStr(ans);
    }

    void reverse(int[] arr, int from, int to) {
        for (; from < to; from++, to--) {
            int x = arr[from];
            arr[from] = arr[to];
            arr[to] = x;
        }
    }

    void swap(int[] arr, int from, int to) {
        int x = arr[to];
        for (int i = to; i > from; i--) {
            arr[i] = arr[i - 1];
        }
        arr[from] = x;
    }

    String getStr(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++)
            sb.append(arr[i]);
        System.out.println(sb.toString());
        return sb.toString();
    }
}