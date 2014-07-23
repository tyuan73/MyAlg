package weekly.week6.day4;

/**
 * Created by yuantian on 7/3/14.
 */
public class Test {
    public static void main(String[] args) {

        int r = 500;
        int c = 500;
        System.out.println(r + " " + c);
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(20 + " ");
            }
            System.out.println();
        }
        int h = 250;
        int w = 250;
        System.out.println(h + " " + w);
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(-20 + " ");
            }
            System.out.println();
        }
    }
}
