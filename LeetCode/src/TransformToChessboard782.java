public class TransformToChessboard782 {
    int n = 0, mask = 0, p0 = 0xAAAAAAAA, p1 = 0x55555555;

    public int movesToChessboard(int[][] board) {
        n = board.length; // the size
        mask = (1 << n) - 1; // the mask, for example: 11111 for n = 5
        p0 &= mask; // the chessboard line ending with 0, for example: 01010 for n = 5
        p1 &= mask; // the chessboard line ending with 1, for example: 10101 for n = 5

        int[] rows = new int[n], cols = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                if (board[i][j] == 1) {
                    rows[i] |= 1 << j;
                    cols[j] |= 1 << i;
                }
        }
        int m1 = minSwap(rows), m2 = minSwap(cols);
        return m1 == -1 || m2 == -1 ? -1 : m1 + m2;
    }

    private int minSwap(int[] nums) {
        int first = nums[0];
        for (int x : nums)
            if (x != first && (x + first) != mask)
                return -1;
        int m = Integer.bitCount(first);
        if ((n % 2) == 0) {
            if (m == n / 2)
                return Math.min(Integer.bitCount(first ^ p0), Integer.bitCount(first ^ p1)) / 2;
        } else {
            if (m == n / 2)
                return Integer.bitCount(first ^ p0) / 2;
            else if (m == n / 2 + 1)
                return Integer.bitCount(first ^ p1) / 2;
        }
        return -1;
    }
}
