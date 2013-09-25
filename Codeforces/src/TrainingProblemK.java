/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 9/25/13
 * Time: 4:57 PM
 * To change this template use File | Settings | File Templates.
 *
 * URL: http://codeforces.com/gym/100231/attachments/download/1868/20132014-ct-s01e03-codeforces-trainings-season-1-episode-3-en.pdf
 */

import java.util.*;

public class TrainingProblemK {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int t = 0;
        while((t = in.nextInt()) != 0) {
            int max = Integer.MIN_VALUE;
            int current = 0;
            for(int i = 0; i < t; i++) {
                current += in.nextInt();
                if(current > max)
                    max = current;
                if(current < 0)
                    current = 0;
            }
            System.out.println(max);
        }
    }
}
