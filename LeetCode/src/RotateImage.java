/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/16/13
 * Time: 12:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int l = 0; l < n / 2; l++) {
            int e = n - 1 - l;
            int low = l, high = e, left = l, right = e;
            for (int s1 = l, s2 = l, s3 = e, s4 = e;
                 s1 < e; s1++, s2++, s3--, s4--) {
                int x = matrix[low][s1];
                matrix[low][s1] = matrix[s4][left];
                matrix[s4][left] = matrix[high][s3];
                matrix[high][s3] = matrix[s2][right];
                matrix[s2][right] = x;
            }
        }
    }

    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int x = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = x;
            }
        }
    }
}
