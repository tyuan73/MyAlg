/**
 * Created by yuantian on 5/30/17.
 */
public class Non_negativeIntegerswithoutConsecutiveOnes600 {
    // Fibonacci
    static int[] oneone = {1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946,
            17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269, 2178309, 3524578};

    /**
     * Recursive solution.
     */
    public int findIntegers(int num) {
        if (num == 0) return 1;
        //if (n == 1) return 2;

        int leftmost = Integer.numberOfLeadingZeros(num);

        // check next bit. If next bit is 1, return related oneone; otherwise make recursive call to next non-zero bit.
        int bit = 1 << (30 - leftmost);
        if ((bit & num) > 0) return oneone[32 - leftmost];
        return oneone[31 - leftmost] + findIntegers(num - (1 << (31 - leftmost)));
    }

    /**
     * Iterative solution.
     */
    public int findIntegers1(int num) {
        int ans = 0;
        for (int bit = 1 << 30, i = 31; i > 0; bit >>= 1, i--) {
            if ((num & bit) == 0) continue; // if current bit is not one, continue
            // if next bit is one, which means there are continues ones in num
            if ((num & (bit >> 1)) != 0) {
                //ans += oneone[i];
                //break;
                return ans + oneone[i]; // if there are continues ones in num, return right away
            }
            ans += oneone[i - 1];
        }

        // if get here, it means no continues 1s in num. For example: 1001, 1010, 1; not: 10110, 1011, 111000
        // We need to add one to the ans in this case.
        return ans + 1;
    }
}
