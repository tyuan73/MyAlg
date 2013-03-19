/**
 * Created with IntelliJ IDEA.
 * User: Michael Tian
 * Date: 3/19/13
 * Time: 10:13 AM
 * Copyright (c) 2013 All Right Reserved, http://github.com/tyuan73
 */

public class Search2DMatrix {
    public static void main(String[] args) {
        Search2DMatrix test = new Search2DMatrix();
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        // search in the first column
        int low = 0, high = n - 1;
        while (low < high) {
            int mid = (low + high + 1) / 2;
            if (matrix[mid][0] == target)
                return true;
            if (matrix[mid][0] < target)
                low = mid;
            else
                high = mid - 1;
        }

        // search in a row
        int left = 0, right = m - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (matrix[low][mid] == target)
                return true;
            if (matrix[low][mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return false;
    }
}
