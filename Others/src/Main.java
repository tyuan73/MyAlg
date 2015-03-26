public class Main {

    public static void main(String[] args) {
        char[] arr = new char[3];
        for (int i = 0; i < 3; i++)
            arr[i] = (char) (i + 'a');

        perm(arr, 0);
    }

    static void perm(char[] arr, int from) {
        if (from == arr.length) {
            System.out.println(arr);
            return;
        }
        for (int j = from; j < arr.length; j++) {
            swap(arr, from, j);
            perm(arr, from + 1);
            swap(arr, from, j);
        }
    }

    static void swap(char[] arr, int l, int r) {
        char ch = arr[l];
        arr[l] = arr[r];
        arr[r] = ch;
    }
}
