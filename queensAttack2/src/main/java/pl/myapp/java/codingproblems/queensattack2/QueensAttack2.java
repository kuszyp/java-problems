package pl.myapp.java.codingproblems.queensattack2;

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
   *  3. INTEGER rq
   *  4. INTEGER cq
   *  5. 2D_INTEGER_ARRAY obstacles
   */
  @SuppressWarnings("java:S3358")
  public int queensAttack(int n, int k, int rq, int cq, List<List<Integer>> obstacles) {
    //    The idea to handle very big board is not to store 3D array as it is but
    //    to store the positions of the obstacles only.

    //    A variable to store non-zero values that represents the obstacles
    Map<Long, Integer> board = new HashMap<>();

    long queenKey = key(rq, cq, n);
    board.put(queenKey, QUEEN);

    for (int i = 0; i < k; i++) {
      List<Integer> obstacle = obstacles.get(i);
      long obstacleKey = key(obstacle.get(0), obstacle.get(1), n);
      board.put(obstacleKey, OBSTACLE);
    }
    int attacks = 0;

    //    up
    int row = rq;
    while (++row <= n && !board.containsKey(key(row, cq, n))) {
      attacks++;
    }

    // down
    row = rq;
    while (--row >= 1 && !board.containsKey(key(row, cq, n))) {
      attacks++;
    }

    // left
    int col = cq;
    while (--col >= 1 && !board.containsKey(key(rq, col, n))) {
      attacks++;
    }

    // right
    col = cq;
    while (++col <= n && !board.containsKey(key(rq, col, n))) {
      attacks++;
    }

    // top-left
    row = rq;
    col = cq;
    while (++row <= n && --col >= 1 && !board.containsKey(key(row, col, n))) {
      attacks++;
    }

    // top-right
    row = rq;
    col = cq;
    while (++row <= n && ++col <= n && !board.containsKey(key(row, col, n))) {
      attacks++;
    }

    // bottom-left
    row = rq;
    col = cq;
    while (--row >= 1 && --col >= 1 && !board.containsKey(key(row, col, n))) {
      attacks++;
    }

    // bottom-right
    row = rq;
    col = cq;
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
