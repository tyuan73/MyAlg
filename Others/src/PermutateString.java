class PermutateString {
    public static void main(String[] args) {
        int n = 3;

        char[] arr = new char[n];
        for (int i = 0; i < n; i++)
            arr[i] = (char) (i + 1 + '0');

        PermutateString.perm(arr, 0);
    }

    static void perm(char[] arr, int start) {
        if (start == arr.length - 1) {
            System.out.println(arr);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            perm(arr, start + 1);
            swap(arr, start, i);
        }
    }

    static void swap(char[] arr, int i, int j) {
        char x = arr[i];
        arr[i] = arr[j];
        arr[j] = x;
    }
}