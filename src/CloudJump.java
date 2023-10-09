import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CloudJump {
    public static int jumpingOnClouds(List<Integer> c) {
        int len = c.size();
        int[] dp = new int[len];

        dp[0] = 0;
        if(c.get(1)==1){
        dp[1]=Integer.MAX_VALUE;
        }else{
            dp[1]=1;
        }

        for (int i = 2; i < len; i++) {
            if (c.get(i) == 1) {
                dp[i] = Integer.MAX_VALUE;
            } else {
                dp[i] = Math.min(dp[i - 1] + 1, dp[i - 2] + 1);
            }
        }

        return dp[len - 1];
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 0, 0, 1, 0};

        List<Integer> ar = new ArrayList<>();
        for (int num : arr) {
            ar.add(num);
        }

        System.out.println(jumpingOnClouds(ar));
    }

}
