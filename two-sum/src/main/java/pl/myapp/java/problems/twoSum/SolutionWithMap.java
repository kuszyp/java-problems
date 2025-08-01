package pl.myapp.java.problems.twoSum;


import java.util.HashMap;
import java.util.Map;

public class SolutionWithMap {

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int subtract = target - nums[i]; // Calculate the remaining value to reach the target
      if (map.containsKey(subtract)) { // Check if we have the index of remaining value
        return new int[] {
          map.get(subtract), i
        }; // Return the current index and the index of the remaining value
      }
      map.put(nums[i], i); // Store the current number and its index in the map
    }
    throw new IllegalArgumentException("No two sum solution");
  }

}
