/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 8/26/14
 * Time: 12:08 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */
public class Candy {
    /*
    one pass and constant extra space.
     */
    public int candy(int[] ratings) {
        int total = ratings.length; // so that everyone got at lease one candy
        int len = 0; // the length of downgoing slope
        int pre = 0; // previous candy on a upgoing slope
        boolean goingDown = false;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] < ratings[i - 1]) {
                // this a downhill slope
                len++;
                goingDown = true;
            } else {
                // if this is from going down to going up
                if (goingDown) {
                    // (len-1)*len/2 is going down triangle minus the highest one.
                    // Math.max(0, len-pre) : make sure the candy of the the highest
                    // one not down calculated
                    total += (len - 1) * len / 2 + Math.max(0, len - pre);
                    len = 0;
                    goingDown = false; // make sure this section is executed only once
                    pre = 0;
                }
                if (ratings[i] == ratings[i - 1]) {
                    // the same rating
                    pre = 0;
                } else {
                    // the next rating is higher
                    total += ++pre;
                }
            }
        }
        // don't forget the last downhill
        if (goingDown) total += (len - 1) * len / 2 + Math.max(0, len - pre);
        return total;
    }


    /*
    two pass and O(n) space, but a lot easier.
     */
    public int candy2(int[] ratings) {
        int len = ratings.length;
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1])
                dp[i] = dp[i - 1] + 1;
        }

        int total = len + dp[len - 2];
        for (int i = len - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                dp[i] = Math.max(dp[i], dp[i + 1] + 1);
            }
            total += dp[i];
        }

        return total;
    }

    public static void main(String[] args) {
        int[] a = {1, 2};
        Candy c = new Candy();
        System.out.println(c.candy2(a));
    }
}
