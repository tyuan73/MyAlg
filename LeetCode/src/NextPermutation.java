/**
 * Created with IntelliJ IDEA.
 * User: Michael
 * Date: 3/11/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class NextPermutation {
    public void nextPermutation(int[] num) {
        int n = num.length;

        int i = n-1;
        while(i > 0 && num[i-1] >= num[i])
            i--;

        if(i > 0) {
            int j = n-1;
            while(j > 0 && num[j] <= num[i-1])
                j--;

            int x = num[i-1];
            num[i-1] = num[j];
            num[j] = x;
        }

        for(int k = i, j = n-1; k < j; k++, j--) {
            int x = num[k];
            num[k] = num[j];
            num[j] = x;
        }
    }
}
