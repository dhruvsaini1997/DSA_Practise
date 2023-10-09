import java.util.Arrays;

public class MovieMarathon {
    public static int maxLen(int[] movies){
        int len = movies.length;
        Arrays.sort(movies);
        int[] dp = new int[len];
        dp[0]=1;
        int max =1;
        for(int i =1;i<len;i++){
            if(movies[i]==movies[i-1] || movies[i]== movies[i-1]+1){
                dp[i]=dp[i-1]+1;
            }else{
                dp[i]=1;
            }
            if(dp[i]>max){
                max=dp[i];
            }
        }
        return max;
    }
    public static void main (String[] args){
        System.out.println(maxLen(new int[]{8, 4, 5, 7, 4}));
    }
}
