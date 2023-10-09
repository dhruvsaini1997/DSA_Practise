import java.net.Inet4Address;
import java.util.*;

public class SameSumPair {
    public static long getTotalEfficiency(List<Integer> arr) {



        Collections.sort(arr);

        int n = arr.size();



        // Determine the required sum

        long reqSum = arr.get(0) + arr.get(n - 1);



        // Initialize pointers and result

        int i = 0, j = n - 1;

        ArrayList<ArrayList<Long>> result = new ArrayList<>();





        while (i < j) {

            long currentSum = arr.get(i) + arr.get(j);

            if (currentSum == reqSum) {

                ArrayList<Long> pair = new ArrayList<>();

                pair.add(Long.valueOf(arr.get(i)));

                pair.add(Long.valueOf(arr.get(j)));

                result.add(pair);

                i++;

                j--;

            } else if (currentSum < reqSum) {

                i++;

            } else {

                j--;

            }

        }



        // Check if the required number of pairs are found

        if (result.size() != n / 2) {

            return -1;

        }



        // Print and return the result

        System.out.println(result);

        long sum = 0;

        for (ArrayList<Long> pair : result) {

            sum += pair.get(0) * pair.get(1);

        }

        return sum;



    }
    public static void main(String[] args){
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(5);
        arr.add(2);
        arr.add(4);
        System.out.println(getTotalEfficiency(arr));
    }
}
