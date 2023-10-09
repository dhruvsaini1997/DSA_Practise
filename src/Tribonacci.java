public class Tribonacci {
  public static int getTribonacci(int n){
    if(n==0){
      return 0;
    }else if(n<3){
      return 1;
    }else{
      int[] dp= new int[n+1];
      dp[1]=1;
      dp[2]=1;
      for(int i =3;i<=n;i++){
        dp[i]= dp[i-1]+dp[i-2]+dp[i-3];
      }

    return dp[n];
    }

  }
  public static void main(String[] args){
    System.out.print(getTribonacci(25));
  }
}

