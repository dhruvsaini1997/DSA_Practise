public class LongestIncreasingPathinMatrix {
    public static int maxLen(int[][] grid){
        int maxLen=0;
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        for(int i =0;i<row;i++){
            for(int j =0;j<col;j++){
                dfs(i,j,grid,dp,grid[i][j]-1);
                maxLen = Math.max(maxLen,dp[i][j]);
            }
        }


        return maxLen;
    }
    public static  int dfs(int i , int j , int[][] grid, int[][] dp, int prevValue){
        if(i == grid.length || j==grid[0].length || i<0 || j<0 || grid[i][j]<=prevValue){
            return 0;
        }
        if(dp[i][j]>0){
            return dp[i][j];
        }
        int up = dfs(i-1,j,grid,dp,grid[i][j]);
        int down = dfs(i+1,j,grid,dp,grid[i][j]);
        int left = dfs(i,j-1,grid,dp,grid[i][j]);
        int right = dfs(i,j+1,grid,dp,grid[i][j]);
        dp[i][j] = 1+ Math.max(Math.max(up,down),Math.max(left,right));

        return dp[i][j];
    }

    public static void main(String[] args){
        int[][] arr = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        System.out.println(maxLen(arr));
    }
}
