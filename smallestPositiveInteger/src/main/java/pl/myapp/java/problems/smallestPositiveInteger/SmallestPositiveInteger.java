package pl.myapp.java.problems.smallestPositiveInteger;

import java.util.*;

public class SmallestPositiveInteger {
  private static final int N = 100000;

  /**
   * This is a naive solution with poor performance for the "Smallest Positive Integer" problem (also known as "First
   * Missing Positive"). At this stage, i hadn't yet recognized the relationship between the array size and the integer
   * value being searched for.
   */
  public int smallestPositiveInteger(int[] A) {
    List<Integer> arr = Arrays.stream(A).boxed().toList();
    HashSet<Integer> hs = new HashSet<>(arr);
    for (int i = 1; i <= N; i++) {
      if (!hs.contains(i)) {
        return i;
      }
    }
    return 1;
  }

  /**
   * One of the most strategic insights in this problem is realizing that when the positive integer is not in the given
   * array, the missing one must be in a range [1, n], where n is the length of the array. Why is that? Let's consider
   * two cases:
   * -  There is no missing integer in the array. For n=5 we have A = [1, 2, 3, 4, 5]. The solution in this case is 6
   *
   * -  There is a missing integer in the array. For n=5 we have B = [1, 2, 4, 6, 8]. The solution is 3.
   *
   * The algorithm works by placing each value into its corresponding index position - for example number 1
   * should go to index 0, 2 to index 1, and so on. The first index that miss its value is the result.
   */
  public int smallestPositiveIntegerCycleSort(int[] A) {
    int n = A.length;
    for (int i = 0; i < n; i++) {
      while (A[i] >= 1 && A[i] <= n && A[i] != A[A[i]-1]) {
        int temp = A[i];
        A[i] = A[A[i]-1];
        A[temp - 1] = temp;
      }
    }

    for (int i = 1; i <= n; i++) {
      if (A[i-1] != i) {
        return i;
      }
    }
    return n + 1;
  }
}
