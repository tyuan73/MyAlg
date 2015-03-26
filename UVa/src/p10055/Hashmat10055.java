package p10055; /**
 * Created by yuantian on 3/26/15.
 */

import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] nums = line.split(" ");
            System.out.println(Math.abs(Long.parseLong(nums[0]) - Long.parseLong(nums[1])));
        }
    }
}
