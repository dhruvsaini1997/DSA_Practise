import java.util.*;

public class HouseSegments {

    public int[] numberHouseSegments(int[] houses, int[] queries) {
        int[] ans = new int[queries.length];

        Set<Integer> destroyed = new HashSet<>();
        destroyed.add(houses[0]);
        Arrays.sort(houses);
        int count = 0;

        // Calculate the initial count of segments
        for (int i = 1; i < houses.length; i++) {
            if (houses[i] != houses[i - 1] + 1) {
                count++;
            }
            destroyed.add(houses[i]);
        }
        count++; // Account for the last segment
int j =0;
        // Process queries
        for (int i : queries) {
            destroyed.remove(i);
            // Check if adjacent houses are both destroyed or not destroyed
            if (destroyed.contains(i - 1) && destroyed.contains(i + 1)) {
                count++;
            } else if (!destroyed.contains(i - 1) && !destroyed.contains(i + 1)) {
                count--;
            }
            ans[j++]=count;
//            result.add(count);
        }

        return ans;
    }

    public static void main(String[] args) {
        HouseSegments s = new HouseSegments();
        int[] houses = {1, 2, 3, 6, 7, 9};
        int[] queries = {6, 3, 7, 2, 9, 1};
       int[] result = s.numberHouseSegments(houses, queries);
       for(int i : result){
        System.out.println(i);
       }
    }
}

