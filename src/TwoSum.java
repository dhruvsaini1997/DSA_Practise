import java.util.*;

// TwoSum problem in O(n) time complexity
public class TwoSum {
    public int[] getSumIndex(int[] nums,int target){
        int[] res = new int[2];
        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<n;i++){
            if(map.containsKey(target-nums[i])){
                res[0]=i;
                res[1]= map.get(target-nums[i]);
                return res;
            }
            map.put(nums[i],i);
        }
        return res;
    }
    public static void main(String[] args){
        TwoSum twoSum = new TwoSum();
        int[] nums = new int[10000];
        int target = 20000;

        // Initialize the array with values such that nums[i] + nums[j] = target for some i, j
        for (int i = 0; i < 5000; i++) {
            nums[i] = i + 1;
            nums[i + 5000] = target - nums[i];
        }

        int[] result = twoSum.getSumIndex(nums, target);

        System.out.println("Indices of the two numbers: " + result[0] + ", " + result[1]);

    }
}
