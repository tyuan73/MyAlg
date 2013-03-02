import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * To change this template use File | Settings | File Templates.
 */
public class FindTheMin {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] input = new String[n * 2];
        for (int i = 0; i < n; i++) {
            input[i * 2] = in.nextLine();
            input[i * 2 + 1] = in.nextLine();
        }
        in.close();

        for (int i = 0; i < n; i++) {
            String[] l1 = input[2 * i].split(" ");
            String[] l2 = input[2 * i + 1].split(" ");

            int ans = process(Integer.parseInt(l1[0]),
                    Integer.parseInt(l1[1]),
                    Integer.parseInt(l2[0]),
                    Integer.parseInt(l2[1]),
                    Integer.parseInt(l2[2]),
                    Integer.parseInt(l2[3])
            );

            System.out.println("Case #" + (i + 1) + ": " + ans);
        }
    }

    static int process(int n, int k, int a, int b, int c, int r) {
        int[] helper = new int[k+3];
        int[] input = getArray(k, a, b, c, r, helper);
        //printArray(input);
        /*
        for(int i = 0; i < n-k; i++) {
            int min = 0;
            while(helper[min] > 0) min++;
            int x = input[i%k];
            if(x <= k)
                helper[x]--;
            helper[min]++;
            input[i%k] = min;
        }
        return input[(n-1)% k];
        */
        int next = 0;
        while (helper[next] > 0) next++;
        int pre = k+3;
        for (int i = k; i < 2*k; i++) {
            //int min = next;
            int index = i % (k+1);
            if(pre < next && helper[pre] == 0) {
                int x = pre;
                pre = input[index];
                input[index] = x;
                //helper[x]--;
                continue;
            }

            if (pre <= k)
                helper[pre]--;
            pre = input[index];
            helper[next]++;
            input[index] = next;
            while (helper[next] > 0) next++;
        }

        //printArray(input);
        return input[(n-1) % (k+1)];
    }

    static int[] getArray(int k, int a, int b, int c, int r, int[] helper) {
        //m[0] = a
        //m[i] = (b * m[i - 1] + c) % r, 0 < i < k
        int[] ret = new int[k+1];
        ret[0] = a;
        if (a <= k) helper[a]++;
        for (int i = 1; i < k; i++) {
            ret[i] = (int)(((long)b * (long) ret[i - 1] + c) % r);
            if (ret[i] <= k)
                helper[ret[i]]++;
        }
        ret[k] = k+3;
        return ret;
    }

    static void printArray(int[] arr) {
        for(int i = 0; i < arr.length; i++)
            System.out.printf("%9d", i);
        System.out.println();
        for(int i : arr)
            System.out.printf("%9d", i);
        System.out.println();
    }
}
