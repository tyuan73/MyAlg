/**
 * Created by yuantian on 4/19/17.
 */

import java.util.*;

public class DesignSnakeGame353 {
    /*
    Not very difficult, but remember to use hashset instead of array which will hit Over Memory Limit error.
    In order to use hashset, we need to turn 2D game board to 1D index that is "X * width + Y"
     */
    LinkedList<int[]> snake = null;
    int score = 0;
    //int[][] board = null; // use array will hit Over Memory Limit exception
    Set<Integer> board = null;
    int width = 0, height = 0;
    int nextFood = 0;
    final static int[][] steps = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][] food = null;

    /**
     * Initialize your data structure here.
     *
     * @param width  - screen width
     * @param height - screen height
     * @param food   - A list of food positions
     *               E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
     */
    public DesignSnakeGame353(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.board = new HashSet<>();
        this.food = food;
        board.add(0);
        snake = new LinkedList<>();
        this.snake.addFirst(new int[]{0, 0});
        this.nextFood = 0;
    }

    /**
     * Moves the snake.
     *
     * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     * @return The game's score after the move. Return -1 if game over.
     * Game over when snake crosses the screen boundary or bites its body.
     */
    public int move(String direction) {
        int[] next = getStep(direction.charAt(0));
        int[] head = snake.getFirst();
        int x = next[0] + head[0], y = next[1] + head[1];
        if (x < 0 || x >= height || y < 0 || y >= width) {
            return -1;
        }

        snake.addFirst(new int[]{x, y});
        if (nextFood < food.length && x == food[nextFood][0] && y == food[nextFood][1]) {
            score++;
            nextFood++;
        } else {
            int[] tail = snake.removeLast();
            //board[tail[0]][tail[1]] = 0;
            board.remove(tail[0] * width + tail[1]);
            if (board.contains(x * width + y))
                return -1;
        }
        board.add(x * width + y);
        return score;
    }

    private int[] getStep(char dir) {
        switch (dir) {
            case 'U':
                return steps[3];
            case 'L':
                return steps[1];
            case 'R':
                return steps[0];
            case 'D':
                return steps[2];
        }
        return new int[]{0, 0};
    }
}
