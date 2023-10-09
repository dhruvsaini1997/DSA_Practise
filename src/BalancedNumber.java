import java.util.ArrayList;
import java.util.List;

public class BalancedNumber {
    public static String countBalancedNumbers(List<Integer> p) {
        int n = p.size();
        StringBuilder ans = new StringBuilder(n);

// Create a set to track the presence of integers in the subsequence
        boolean[] presence = new boolean[n + 1];

        for (int k = 1; k <= n; ++k) {
            boolean found = false;

// Initialize the presence array for each subsequence
            for (int i = 1; i <= n; ++i) {
                presence[i] = false;
            }

            int countNum = 0;

            for (int r = 0; r < n; ++r) {
// Add the current element to the presence array
                int current = p.get(r);
                presence[current] = true;

// If the current element is one of the first k integers, increment countNum
                if (current <= k) {
                    countNum++;
                }

// If we have all integers from 1 to k, found is set to true
                if (countNum == k) {
                    found = true;
                    break;
                }

// If the subsequence length is greater than k, remove the leftmost element
                if (r >= k - 1) {
                    int leftmost = p.get(r - k + 1);
                    presence[leftmost] = false;
                    if (leftmost <= k) {
                        countNum--;
                    }
                }
            }

            if (found) {
                ans.append('1');
            } else {
                ans.append('0');
            }
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(2);
        System.out.println(countBalancedNumbers(list)); // Output should be "1001"
    }
}
