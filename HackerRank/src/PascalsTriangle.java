public class PascalsTriangle {
    public static void main(String[] args) {
        int i = 31,j;
        int[] t = new int[i];
        t[30] = 1;
        while(i-->0) {
            j=i-1;
            while(j++<29) {
                t[j] += t[j+1];
                System.out.print(t[j]+" ");
            }
            System.out.println(1);
        }
    }
}
