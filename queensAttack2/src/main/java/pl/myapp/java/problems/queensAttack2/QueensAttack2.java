package pl.myapp.java.problems.queensAttack2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueensAttack2 {
  private static final int QUEEN = 1;
  private static final int OBSTACLE = -1;

  /*
   * Complete the 'queensAttack' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER n
   *  2. INTEGER k
   *  3. INTEGER r_q
   *  4. INTEGER c_q
   *  5. 2D_INTEGER_ARRAY obstacles
   */
  public int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
//    The idea to handle very big board is not to store 3D array as it is but to store the positions
//    on the obstacles only.

//    A variable to store non-zero values that represents the obstacles
    Map<Long, Integer> board = new HashMap<>();

    long queenKey = key(r_q, c_q, n);
    board.put(queenKey, QUEEN);

    obstacles.forEach(
      obstacle -> {
        long obstacleKey = key(obstacle.get(0), obstacle.get(1), n);
        board.put(obstacleKey, OBSTACLE);
      });

    int attacks = 0;

//    up
    int row = r_q;
    while (++row <= n && !board.containsKey(key(row, c_q, n))) {
      attacks++;
    }

    // down
    row = r_q;
    while (--row >= 1 && !board.containsKey(key(row, c_q, n))) {
      attacks++;
    }

    // left
    int col = c_q;
    while (--col >= 1 && !board.containsKey(key(r_q, col, n))) {
      attacks++;
    }

    // right
    col = c_q;
    while (++col <= n && !board.containsKey(key(r_q, col, n))) {
      attacks++;
    }

    // top-left
    row = r_q;
    col = c_q;
    while (++row <= n && --col >= 1 && !board.containsKey(key(row, col, n))) {
      attacks++;
    }

    // top-right
    row = r_q;
    col = c_q;
    while (++row <= n && ++col <= n && !board.containsKey(key(row, col, n))) {
      attacks++;
    }

    // bottom-left
    row = r_q;
    col = c_q;
    while (--row >= 1 && --col >= 1 && !board.containsKey(key(row, col, n))) {
      attacks++;
    }

    // bottom-right
    row = r_q;
    col = c_q;
    while (--row >= 1 && ++col <= n && !board.containsKey(key(row, col, n))) {
      attacks++;
    }

    return attacks;
  }

//  Instead of identifying the position of obstacles in 2D array
//  using (x, y) coordinates, we can use a single long value as a key.
//  The value must be unique for each (i, j) pair.
  long key(int i, int j, int n) {
    return ((long) i) * n + j;
  }

}
