import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayReduction {
  public static List<Integer> getMaxArray(int[] arr) {
    List<Integer> arrList = new ArrayList<>();
    for (int num : arr) {
      arrList.add(num);
    }

    // Sort the list in non-ascending order
//    Collections.sort(arrList, Collections.reverseOrder());
    List<Integer> result = new ArrayList<>();

    while (!arrList.isEmpty()) {
      int k = (arrList.get(0) == 0) ? 1 : 2;

      int mex = 0;
      for (int i = 0; i < k; i++) {
        if (!arrList.contains(mex)) {
          break;
        }
        mex++;
      }

      result.add(mex);

      // Remove the first k elements from arrList
      for (int i = 0; i < k; i++) {
        arrList.remove((i));
      }
    }

    return result;
  }

  public static void main(String[] args) {
    int[] arr = {0, 1, 1, 0};
    List<Integer> result = getMaxArray(arr);
    System.out.println(result); // Output: [2, 2]
  }
}
