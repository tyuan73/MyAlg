package test;

/**
 * Created by yuantian on 6/27/14.
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] a = {7,6,5,4,3,2,1};

        for(int i = 1; i < a.length; i++) {
            int cur = a[i];
            int j = i;
            while(j > 0) {
                if (cur < a[j-1])
                    a[j] = a[j-1];
                else
                    break;
                j--;
            }
            a[j] = cur;
        }

        for(int i: a)
        System.out.print(" " + i);
        System.out.println();
    }
}
