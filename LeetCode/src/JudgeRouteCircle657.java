


public class JudgeRouteCircle657 {
    /**
     * Naive solution
     */
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (int i = 0; i < moves.length(); i++) {
            switch (moves.charAt(i)) {
                case 'U':
                    x++;
                    break;
                case 'D':
                    x--;
                    break;
                case 'L':
                    y--;
                    break;
                case 'R':
                    y++;
                    break;
            }
        }
        return (x == 0) && (y == 0);
    }

    /**
     * Count L, U, D, and R. count('L') == count('R') && count('U') == count('D')
     */
    public boolean judgeCircle1(String moves) {
        int[] count = new int[128];
        for (int i = 0; i < moves.length(); i++)
            count[moves.charAt(i)]++;
        return count['L'] == count['R'] && count['D'] == count['U'];
    }
}
