package pl.myapp.java.problems.smallestPositiveInteger;

import java.util.*;

public class SmallestPositiveInteger {
  private static final int N = 100000;

  /*
  This is a naive solution with poor performance for the "Smallest Positive Integer" problem (also known as "First
  Missing Positive"). At this stage, i hadn't yet recognized the relationship between the array size and the integer
  value being searched for.
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
}
