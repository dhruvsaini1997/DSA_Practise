import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class Integer_Permutation {
  public List<List<Integer>> getPermutation(int[] nums){
    List<List<Integer>> result = new ArrayList<>();
    int len = nums.length;
    boolean[] isUsed= new boolean[len];
    List<Integer> permutations = new ArrayList<>();
    return backtrack(nums,result,isUsed,permutations);
  }
  private List<List<Integer>> backtrack(int[]nums, List<List<Integer>> res,boolean[] used,List<Integer> perm){
    if(perm.size()==nums.length){
      res.add(new ArrayList<>(perm));
      return res;
    }
    for(int i =0;i<nums.length;i++){
      if(!used[i]){
        used[i]= true;
        perm.add(nums[i]);
        backtrack(nums,res,used,perm);
        used[i]=false;
        perm.remove(perm.size()-1);
      }
    }
    return res;
  }
  public static void main(String[] args){
    Integer_Permutation obj = new Integer_Permutation();
    System.out.println(obj.getPermutation(new int[]{1,2,3}));
  }
}
