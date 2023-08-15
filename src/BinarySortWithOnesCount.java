import java.util.*;

public class BinarySortWithOnesCount {
    public static int countOnes(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n >>= 1;
        }
        return count;
    }

    public static List<Integer> binarySortWithOnesCount(List<Integer> nums) {
        Map<Integer, List<Integer>> onesCountMap = new HashMap<>();

        for (int num : nums) {
            int onesCount = countOnes(num);
            onesCountMap.computeIfAbsent(onesCount, k -> new ArrayList<>()).add(num);
        }

        List<Integer> sortedNums = new ArrayList<>();
        for (int onesCount : onesCountMap.keySet()) {
            List<Integer> numsWithOnesCount = onesCountMap.get(onesCount);
            Collections.sort(numsWithOnesCount);
            sortedNums.addAll(numsWithOnesCount);
        }

        return sortedNums;
    }

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(Arrays.asList(8, 5, 6, 7));
        List<Integer> sortedNums = binarySortWithOnesCount(nums);
        System.out.println(sortedNums);
    }
}
