package pl.myapp.java.problems.queensAttack2;

import java.util.List;

public class QueensAttack2 {
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
    // Write your code here
    int[][] board = new int[n][n];
    int attacks = 0;
    board[r_q - 1][c_q - 1] = 1;
    obstacles.forEach(obstacle -> board[obstacle.get(0) - 1][obstacle.get(1) - 1] = -1);

    int row = r_q - 1;
    int col = c_q - 1;
    // bottom
    while (row - 1 >= 0) {
      if (board[row-1][col] == -1) {
        break;
      }
      board[row-1][col] = 2;
      attacks++;
      row--;
    }

    // top
    row = r_q - 1;
    col = c_q - 1;
    while (row + 1 < n) {
      if (board[row + 1][col] == -1) {
        break;
      }
      board[row + 1][col] = 2;
      attacks++;
      row++;
    }

    // left
    // right
    row = r_q - 1;
    col = c_q - 1;
    while (col + 1 < n) {
      if (board[row][col + 1] == -1) {
        break;
      }
      board[row][col + 1] = 2;
      attacks++;
      col++;
    }

    // top-left
    row = r_q - 1;
    col = c_q - 1;
    while (row + 1 < n && col - 1 >= 0) {
      if (board[row + 1][col - 1] == -1) {
        break;
      }
      board[row + 1][col - 1] = 2;
      attacks++;
      row++;
      col--;
    }

    // top-right
    row = r_q - 1;
    col = c_q - 1;
    while (row + 1 < n && col + 1 < n) {
      if (board[row + 1][col + 1] == -1) {
        break;
      }
      board[row + 1][col + 1] = 2;
      attacks++;
      row++;
      col++;
    }
    // bottom-left
    // bottom-right

    return attacks;
  }
}
