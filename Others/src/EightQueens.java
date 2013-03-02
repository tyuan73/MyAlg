/**
 * Created with IntelliJ IDEA.
 * User: yuantian
 * Date: 1/24/13
 * Time: 3:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class EightQueens {
    private int total = 0;

    public static void main(String[] args) {
        int[] rows = new int[8];
        boolean[] used = new boolean[8];

        EightQueens e = new EightQueens();
        e.calculate(rows, 0, used);
        System.out.println("total = "+ e.total);
    }

    void calculate(int[] rows, int index, boolean[] used) {
        if(index == 8) {
            total++;
            return;
        }

        for(int i = 0; i < 8; i++) {
            if(used[i]) continue;
            used[i] = true;
            rows[index] = i;
            if(valid(rows, index))
                calculate(rows, index+1, used);
            used[i] = false;
        }
    }

    boolean valid(int[] rows, int index) {
        for(int i = 0; i < index; i++) {
            if(Math.abs(index - i) == Math.abs(rows[index] - rows[i]))
               return false;
        }

        return true;
    }
}
